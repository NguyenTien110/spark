/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hive.service.auth;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class helps in some aspects of authentication. It creates the proper Thrift classes for the
 * given configuration as well as helps with authenticating requests.
 */
public class HiveLoginMysql {
    Logger logger = Logger.getLogger(HiveLoginMysql.class.getName());

    public boolean connectDatabase(String username, String password) {
        String url = "jdbc:mysql://" +
                System.getenv().getOrDefault("HIVE_MYSQL_HOST", "localhost")
                + ":" +
                System.getenv().getOrDefault("HIVE_MYSQL_PORT", "3306") + "/metastore_db";
        try {
            System.out.println(url);
            System.out.println(username);
            System.out.println(password);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException e) {
            logger.log(Level.INFO,"Cannot connect to db!");
            return false;
        } catch (ClassNotFoundException e) {
            logger.log(Level.INFO,"ClassNotFoundException mysql!");
            return false;
        }
    }

    public static void main(String[] args) {
        HiveLoginMysql a = new HiveLoginMysql();
        System.out.println(a.connectDatabase("team_data", "KTQhkLYfP3nV33MH"));
    }
}
