package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Main {
    /**
     * database url.
     */
    private static String fDBURL = "jdbc:mysql://localhost:3306/country_city";

    /**
     * database username.
     */
    private static String fUSERNAME = "root";

    /**
     * database password.
     */
    private static String fPASSWORD = "diep171101";

    private Main() {

    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();

            System.out.println("2.1. Tim thanh pho dong"
                    + " dan nhat cua moi quoc gia.");
            getResult1(stmt);

            System.out.println("2.2. Tim thanh pho dong"
                    + " dan nhat cua moi luc dia.");
            getResult2(stmt);

            System.out.println("2.3. Tim thanh pho la thu do"
                    + " va dong dan nhat.");
            getResult3(stmt);

            System.out.println("2.4. Tim thanh pho la thu do"
                    + " va dong dan nhat cua moi luc dia.");
            getResult4(stmt);

            System.out.println("2.5. Sap xep cac quoc gia theo so luong"
                    + " thanh pho giam dan.");
            getResult5(stmt);

            System.out.println("2.5. Sap xep cac quoc gia theo"
                    + " mat do dan so giam dan"
                    + " bo qua cac quoc gia co dan so bang 0.");
            getResult6(stmt);

            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Ket qua.
     * 2.1. Tim thanh pho dong dan nhat cua moi quoc gia.
     * @param statement
     */
    public static void getResult1(final Statement statement)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery(getQuery1());
        while (resultSet.next()) {
            System.out.println(
                    "Country: " + resultSet.getString("Country_name") + ","
                            + " City: " + resultSet.getString("City_name") + ","
                            + " Population: " + resultSet.getInt("population"));
        }
        resultSet.close();
    }

    /**
     * 2.1. Tim thanh pho dong dan nhat cua moi quoc gia.
     * @return query of question 2.1.
     */
    public static String getQuery1() {
        return "select Country.name as Country_name, "
                + "City.name as City_name, City.population "
                + "from Country, City "
                + "where Country.id = City.country_id "
                + "and City.population = (select max(population) "
                + "from City where Country.id = City.country_id);";
    }

    /**
     * Ket qua.
     * 2.2. Tim thanh pho dong dan nhat cua moi luc dia.
     * @param statement
     */
    public static void getResult2(final Statement statement)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery(getQuery2());
        while (resultSet.next()) {
            System.out.println(
                    "Contient: " + resultSet.getString("Contient_name") + ","
                    + " Country : " + resultSet.getString("Country_name") + ","
                    + " City: " + resultSet.getString("City_name") + ","
                    + " Population: " + resultSet.getInt("max"));
        }
        resultSet.close();
    }

    /**
     * 2.2. Tim thanh pho dong dan nhat cua moi luc dia.
     * @return query of question 2.2.
     */
    public static String getQuery2() {
        return "select Contient.name as Contient_name,"
                + " Country.name as Country_name, "
                + " City.name as City_name, "
                + " temp.max"
                + " from Contient, City, Country, (select Contient.id, "
                                    + " max(City.population) as max "
                                    + "from City, Country, Contient "
                                    + "where City.country_id = Country.id "
                                    + "and Country.contient = Contient.id "
                                    + "group by Contient.id) as temp "
                + "where Contient.id = Country.contient "
                + "and Country.id = City.country_id "
                + "and temp.id = Contient.id "
                + "and City.population = temp.max;";
    }

    /**
     * Ket qua.
     * 2.3. Tim thanh pho la thu do va dong dan nhat.
     * @param statement
     */
    public static void getResult3(final Statement statement)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery(getQuery3());
        while (resultSet.next()) {
            System.out.println(
                            "City: " + resultSet.getString("name") + ","
                            + " Population: " + resultSet.getInt("population"));
        }
        resultSet.close();
    }

    /**
     * 2.3. Tim thanh pho la thu do va dong dan nhat.
     * @return query of question 2.3.
     */
    public static String getQuery3() {
        return "select City.id, City.name, City.population "
                + "from City, Country, Capital "
                + "where City.country_id = Country.id "
                + "and City.id = Capital.city_id "
                + "and Country.id = Capital.country_id "
                + "order by City.population desc "
                + "limit 1;";
    }

    /**
     * Ket qua.
     * 2.4. Tim thanh pho la thu do va dong dan nhat cua moi luc dia.
     * @param statement
     */
    public static void getResult4(final Statement statement)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery(getQuery4());
        while (resultSet.next()) {
            System.out.println(
                    "Contient: " + resultSet.getString("Contient_name") + ","
                    + " Country : " + resultSet.getString("Country_name") + ","
                    + " City: " + resultSet.getString("City_name") + ","
                    + " Population: " + resultSet.getInt("max"));
        }
        resultSet.close();
    }

    /**
     * 2.4. Tim thanh pho la thu do va dong dan nhat cua moi luc dia.
     * @return query of question 2.4.
     */
    public static String getQuery4() {
        return "select Contient.name as Contient_name,"
                + " Country.name as Country_name,"
                + " City.name as City_name, temp.max "
                + "from Contient, City, Country, (select Contient.id, "
                                    + "max(City.population) as max "
                                    + "from City, Country, Contient, Capital "
                                    + "where City.country_id = Country.id "
                                    + "and Country.contient = Contient.id "
                                    + "and Capital.city_id = City.id "
                                    + "group by Contient.id) as temp "
                + "where Contient.id = Country.contient "
                + "and Country.id = City.country_id "
                + "    and temp.id = Contient.id "
                + "    and City.population = temp.max;";
    }

    /**
     * Ket qua.
     * 2.5. Sap xep cac quoc gia theo so luong thanh pho giam dan.
     * @param statement
     */
    public static void getResult5(final Statement statement)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery(getQuery5());
        while (resultSet.next()) {
            System.out.println(
                    "Country : " + resultSet.getString("name") + ","
                    + " So luong thanh pho: "
                            + resultSet.getInt("so_luong_thanh_pho"));
        }
        resultSet.close();
    }

    /**
     * 2.5. Sap xep cac quoc gia theo so luong thanh pho giam dan.
     * @return query of question 2.5.
     */
    public static String getQuery5() {
        return "select Country.name, count(City.id) as so_luong_thanh_pho "
                + "from Country, City "
                + "where Country.id = City.country_id "
                + "group by Country.name "
                + "order by so_luong_thanh_pho desc;";
    }

    /**
     * Ket qua.
     * 2.6. Sap xep cac quoc gia theo mat do dan so co thu tu .
     * giam dan bo qua cac quoc gia co dan so bang 0.
     * @param statement
     */
    public static void getResult6(final Statement statement)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery(getQuery6());
        while (resultSet.next()) {
            System.out.println(
                    "Country : " + resultSet.getString("name") + ","
                    + " So luong thanh pho: "
                            + resultSet.getDouble("mat_do_dan_so"));
        }
        resultSet.close();
    }

    /**
     * 2.6. Sap xep cac quoc gia theo mat do dan so co thu tu .
     * giam dan bo qua cac quoc gia co dan so bang 0.
     * @return query of question 2.6.
     */
    public static String getQuery6() {
        return "select Country.name, "
                + "(sum(City.population)/Country.surfaceArea) as mat_do_dan_so "
                + "from Country, City "
                + "where Country.id = City.country_id "
                + "group by Country.name, Country.surfaceArea "
                + "having sum(City.population) > 0 "
                + "order by mat_do_dan_so desc;";
    }

    /**
     * create connection.
     * @return connection
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(fDBURL, fUSERNAME, fPASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
