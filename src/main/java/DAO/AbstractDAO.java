package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.ConnectionFactory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Aceasta clasa efectueaza operatiile de SELECT *, SELECT, INSERT, DELETE si UPDATE
 * asupra tabelelor din baza de date.
 * @author Tania
 * @param <T> este clasa ce reprezinta modelul tabelului din baza de date asupra caruia se efectueaza operatii.
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public JTable createTable(List<T> list) {
        ArrayList<String> columns = new ArrayList<>();
        for(Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            columns.add(field.getName());
        }
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns.toArray());
        for(Object obj : list) {
            ArrayList<Object> objects = new ArrayList<>();
            for(Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    objects.add(field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(objects.toArray());
        }
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        return table;
    }

    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM tema3tp.");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM tema3tp.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM tema3tp.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String createInsertQuery(ArrayList<String> field) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append("INTO tema3tp.");
        sb.append(type.getSimpleName());
        sb.append(" (");
        for(int i=0; i<field.size();i++) {
            if(i<field.size()-1)
                sb.append(field.get(i) + ", ");
            else
                sb.append(field.get(i));
        }
        sb.append(")");
        sb.append(" VALUES ");
        sb.append("(");
        for(int i=0;i<field.size();i++) {
            if(i<field.size()-1)
                sb.append("?" + ",");
            else
                sb.append("?");
        }
        sb.append(")");
        return sb.toString();
    }

    public String createUpdateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tema3tp.");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        String prefix = "";
        Object value;
        try {
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                field.setAccessible(true);
                sb.append(prefix);
                prefix = ",";
                sb.append(fieldName);
                sb.append(" = ");

                value = field.get(t);
                if(value instanceof String)
                    sb.append("'");
                sb.append(value.toString());
                if(value instanceof String)
                    sb.append("'");
            }
            sb.append(" WHERE id = ");
            Field idField = type.getDeclaredFields()[0];
            idField.setAccessible(true);
            value = idField.get(t);
            sb.append(value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            if(resultSet.next())
                return createObjects(resultSet).get(0);
            else
                return null;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | SQLException | InvocationTargetException | IllegalArgumentException |
                 IntrospectionException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery("id");
        boolean ok=true;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
            ok=false;

        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return ok;
    }

    public boolean deleteByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery("name");
        boolean ok=true;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteByName " + e.getMessage());
            ok=false;

        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return ok;
    }

    public T insert(T t) {
        ReflectionOperator list = new ReflectionOperator();
        list.retrieveProperties(t);
        ArrayList<String>listF=list.getListField() ;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(listF);
        boolean ok=true;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            ArrayList<Object>listV=list.getListValue();

            int i=1;
            for(Object value:listV) {
                System.out.println(value);
                if(value.getClass().getSimpleName().equals("Integer")) {
                    statement.setInt(i, (int) value);
                }
                if(value.getClass().getSimpleName().equals("String")) {
                    statement.setString(i, (String) value);
                }
                if(value.getClass().getSimpleName().equals("Float")) {
                    statement.setFloat(i, (float) value);
                }
                i++;
            }

            System.out.println(statement.toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
            ok=false;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        if(ok)
            return t;
        else
            return null;
    }

    public T update(T t) {
        String query = createUpdateQuery(t);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
}

