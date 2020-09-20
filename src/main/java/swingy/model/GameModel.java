package swingy.model;

import swingy.controller.ConsoleController;
import swingy.model.artifact.Armor;
import swingy.model.artifact.Helm;
import swingy.model.artifact.Weapon;
import swingy.model.character.heros.Hero;
import swingy.model.character.CharacterType;
import swingy.model.character.villian.Villian;
import swingy.utils.database.Database;
import swingy.model.character.CharacterFactory;
import swingy.utils.GridFactory;
import swingy.view.console.ConsoleView;
import org.jetbrains.annotations.NotNull;
import swingy.utils.Colors;
import swingy.utils.Globals;
import swingy.utils.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static swingy.utils.Colors.*;
import static swingy.utils.Log.inputSign;
import static swingy.utils.Log.log;
import static swingy.view.console.ConsoleView.goodbye;


//TODO convert to wrapper
public class GameModel {
    private static GameModel instance;
    private static Connection conn = Database.getConnection();
    private static int[] previousPosition = new int[2];

    private GameModel() {}

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public void setupDatabase() {
        createTable();
    }

    private void createTable() {
        String sql = String.format("CREATE TABLE IF NOT EXISTS heroes (\nheroID INTEGER PRIMARY KEY," +
                "\nheroName TEXT NOT NULL UNIQUE ,\nheroClass TEXT NOT NULL ,\nheroLevel INTEGER NOT NULL ," +
                "\nheroExperience INTEGER NOT NULL ,\nheroHP INTEGER NOT NULL ,\nheroAttack INTEGER NOT NULL ," +
                "\nheroDefense INTEGER NOT NULL \n );");

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("table added");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nError: cannot create table");
            System.exit(-1);
        }
    }

    public boolean heroExists(String name) throws SQLException {
        boolean exists = false;
        String sql = "SELECT * FROM heroes WHERE heroName='" + name + "'";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        if (resultSet.next()) {
            exists = true;
        }
        return exists;
    }

    public void insertHero(Hero newHero) throws SQLException {
        String sql = "INSERT INTO heroes (" +
                "heroName, heroClass, " +
                "heroLevel, heroExperience, " +
                "heroHP, heroAttack, heroDefense) " +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, newHero.getName());
        pstmt.setString(2, newHero.getType());
        pstmt.setInt(3, newHero.getLevel());
        pstmt.setInt(4, newHero.getExperience());
        pstmt.setInt(5, newHero.getHitPoints());
        pstmt.setInt(6, newHero.getAttack());
        pstmt.setInt(7, newHero.getDefense());
        pstmt.execute();
    }

    public int numberOfHeroes() throws SQLException {
        int rowCount = 0;
        String sql = "SELECT * FROM heroes";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next())
            rowCount++;
        return rowCount;
    }

    public List<Hero> retrieveAllHeroes() throws SQLException {
        String sql = "SELECT * FROM heroes";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        return (parseResult(resultSet));
    }

//    TODO use prepared statement
    public Hero retrieveHeroData(String name) throws SQLException {
        String sql = String.format("SELECT * FROM heroes WHERE heroName='%s'", name);
        Statement stmt = conn.createStatement();
        ResultSet resultSet =  stmt.executeQuery(sql);
        return parseResult(resultSet).get(0);
    }

//    TODO handle resultset validation
    @NotNull
    private List<Hero> parseResult(ResultSet rs) throws SQLException {
//        conn  = Database.getConnection();
        List<Hero> list = new ArrayList<>();
        CharacterType type = null;
        while (rs.next()) {
            String heroType = rs.getString("heroClass").toUpperCase();
            switch (heroType) {
                case "DEADPOOL":
                    type = CharacterType.DEADPOOL;
                    break;
                case "THOR":
                    type = CharacterType.THOR;
                    break;
                case "WOLVERINE":
                    type = CharacterType.WOLVERINE;
                    break;
            }
            assert type != null;
            Hero hero = CharacterFactory.newHero(rs.getString("heroName"), type);
            hero.setId(rs.getInt("heroID"));
            hero.setLevel(rs.getInt("heroLevel"));
            hero.setHitPoints(rs.getInt("heroHP"));
            hero.setExperience(rs.getInt("heroExperience"));
            hero.setAttack(rs.getInt("heroAttack"));
            hero.setDefense(rs.getInt("heroDefense"));
            list.add(hero);
        }
        return list;
    }

//    TODO use prepared statement
    public void updateHero(@NotNull Hero hero) throws SQLException {
        String sql = String.format("UPDATE heroes " +
                "SET heroAttack='%s', " +
                "heroDefense='%s', " +
                "heroExperience='%s', " +
                "heroHP='%s', " +
                "heroLevel='%s' WHERE heroName='%s'", hero.getAttack(), hero.getDefense(), hero.getExperience(), hero.getHitPoints(), hero.getLevel(), hero.getName());
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public List<Hero> retrieveDatabase() {
        return new ArrayList<Hero>();
    }


    //    TODO Static methods
    public static void moveHero(int direction) {
        switch (direction) {
            case 1:
                Globals.hero.setPosition(-1, 0);
                previousPosition[0] = -1;
                previousPosition[1] = 0;
                break;
            case 2:
                Globals.hero.setPosition(0, 1);
                previousPosition[0] = -1;
                previousPosition[1] = 0;
                break;
            case 3:
                Globals.hero.setPosition(0, -1);
                previousPosition[0] = -1;
                previousPosition[1] = 0;
                break;
            case 4:
                Globals.hero.setPosition(1, 0);
                previousPosition[0] = -1;
                previousPosition[1] = 0;
                break;
        }
        if (Globals.grid.getMap()[Globals.hero.getXCoordinate()][Globals.hero.getYCoordinate()] == 'X') {
            int random = new Random().nextInt(3);
            if (random == 2) {
                Globals.villian = (Villian) CharacterFactory.newEnemy(Globals.hero, CharacterType.MAGNETO);
            } else {
                Globals.villian = (Villian) CharacterFactory.newEnemy(Globals.hero, CharacterType.ULTRON);
            }
            if (Globals.CONSOLE_MODE) {
                action();
            }
        }
    }




//    TODO move to base controller
    public static void action() {
        Scanner scanner = new Scanner(System.in);

        ConsoleView.showActionOption(Globals.villian);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            int choice = Integer.parseInt(input);

            if (choice == 1 || choice == 2) {
                switch (choice) {
                    case 1:
                        fight();
                        return;
                    case 2:
                        run();
                        return;
                    default:
                        break;
                }
            } else {
                //                TODO refactor
                Log.log(Colors.ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-2). Try Again!\n" + Colors.ANSI_RESET);
                Log.inputSign();
//                log("Try again!");
                ConsoleView.showActionOption(Globals.villian);
            }
        }

    }


//    TODO move to model base controller
    public static void fight() {
        if (!Globals.HERO_RAN) {
//            TODO refactor to model class
            while (Globals.hero.getHitPoints() > 0 && Globals.villian.getHitPoints() > 0) {
                Globals.hero.attack(Globals.villian);
                if (Globals.villian.getHitPoints() > 0) {
                    Globals.villian.attack(Globals.hero);
                }
            }
        } else {
            while (Globals.hero.getHitPoints() > 0 && Globals.villian.getHitPoints() > 0) {
                Globals.villian.attack(Globals.hero);
                if (Globals.hero.getHitPoints() > 0) {
                    Globals.hero.attack(Globals.villian);
                }
            }
        }
        if (Globals.hero.getHitPoints() <= 0) {
            if (Globals.CONSOLE_MODE) {
                ConsoleView.gameOver();
            }
        } else {
            try {
                GameModel.getInstance().updateHero(Globals.hero);
                Globals.hero.setPosition(0, 0);
                battleGains();
                Log.log(Colors.ANSI_GREEN + ":::" + "Congratulations, You Won The Battle!" + Colors.ANSI_RESET);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
//            catch (ClassNotFoundException | SQLException | IOException e) {
//                e.printStackTrace();
//            }
        }
    }

//    TODO move to base controller
    public static void run() {
        int chance = new Random().nextInt(2);
        Scanner scanner = new Scanner(System.in);

        if (chance == 1) {
            //            TODO Refactor
            log(Colors.ANSI_YELLOW + ":::Hahaha, You Can't Run My Friend, We Gonna Fight!" + Colors.ANSI_RESET);
            log(ANSI_RED + "1." + ANSI_RESET + " Fight");
            log(ANSI_RED + "2." + ANSI_RESET + " Quit");
            inputSign();
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                if (input.toLowerCase().equals("1")) {
                    fight();
                    break;
                } else if (input.toLowerCase().equals("2")) {
                    goodbye();
                } else {
                    log(Colors.ANSI_RED + ":::ERROR::: Incorrect choice, please choose between (1-2). Try Again!" + Colors.ANSI_RESET);
                    inputSign();
                }
            }
            Globals.HERO_RAN = false;
        } else {
            Globals.HERO_RAN = true;
            Log.log(Colors.ANSI_RED + ":::Coward! You Ran Away!" + Colors.ANSI_RESET);
            Globals.hero.setPosition(previousPosition[0] * -1, previousPosition[1] * -1);
        }
    }


//    TODO move to model
    private static void battleGains() {
        int drop = new Random().nextInt(2);
        boolean artifactIsDropped = drop == 1;

        if (artifactIsDropped) {
            Globals.ARTIFACT_DROPPED = true;
            try {
                Log.log(Colors.ANSI_YELLOW + ":::Artifact is Dropped!" + ANSI_RESET);
                String[] artifacts = {"ARMOR", "HELM", "WEAPON", "EXPERIENCE"};
                String artifactType = artifacts[new Random().nextInt(4)];
                int variety = Globals.hero.getLevel() + 1;

                if ("ARMOR".equals(artifactType)) {
                    Globals.artifact = new Armor("Dropped Armor", variety);
                    int gainedDefense = (((Armor) Globals.artifact).getDefense() + Globals.hero.getArmor().getDefense());
                    ((Armor) Globals.artifact).setDefense(gainedDefense);
                    Log.log(Colors.ANSI_YELLOW + ":::" + ANSI_RESET + ANSI_CYAN + "::: If You Keep This Artifact Your Defense Increases by " + gainedDefense + "." + ANSI_RESET);
                } else if ("HELM".equals(artifactType)) {
                    Globals.artifact = new Helm("Dropped Helmet", variety);
                    int gainedHitPoints = (((Helm) Globals.artifact).getHitPoints() + Globals.hero.getHelm().getHitPoints());
                    ((Helm) Globals.artifact).setHitPoints(gainedHitPoints);
                    Log.log(Colors.ANSI_YELLOW + ":::" + ANSI_RESET + ANSI_CYAN +"::: If You Keep This Artifact Your Hit Point(s) Increase by " + gainedHitPoints + "." + ANSI_RESET);
                } else if ("WEAPON".equals(artifactType)) {
                    Globals.artifact = new Weapon("Dropped Weapon", variety);
                    int gainedAttack = (((Weapon) Globals.artifact).getAttack() + Globals.hero.getWeapon().getAttack());
                    ((Weapon) Globals.artifact).setAttack(gainedAttack);
                    Log.log(Colors.ANSI_YELLOW + ":::" + ANSI_RESET + ANSI_CYAN + "If You Keep This Artifact Your Attack Increases by " + gainedAttack + "." + ANSI_RESET);
                } else if ("EXPERIENCE".equals(artifactType)) {
                    Globals.hero.setHitPoints(Globals.hero.getHitPoints() + variety);
                    Log.log(Colors.ANSI_YELLOW + ":::" + ANSI_RESET + ANSI_CYAN +"::: Healed Up, Current Health: " + Globals.hero.getHitPoints() + ANSI_RESET);
                    return;
                }
                // Equip the character.
                equip(artifactType);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (!artifactIsDropped) {
            Log.log( Colors.ANSI_RED + ":::Sorry, No Artifact Dropped!" + ANSI_RESET);
        }
    }


//    TODO move to model
    private static void equip(String artifactType) {
        if (Globals.CONSOLE_MODE) {
            Scanner scanner = new Scanner(System.in);
            Log.log(Colors.ANSI_YELLOW + ":::" + ANSI_RESET + ANSI_CYAN + "Do You Wanna Keep The Artifact?" + Colors.ANSI_RESET);
            Log.log(ANSI_RED + "1." + ANSI_RESET + "Yes");
            Log.log(ANSI_RED + "2." + ANSI_RESET + "NO");
            Log.inputSign();
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equals("1") || input.equals("2")) {
                    int choice = Integer.parseInt(input.trim());
                    if (choice == 1) {
                        Globals.hero.equipHero(Globals.artifact, Globals.artifact.getType());
                        Log.log("::: " + Globals.hero.getName() + " Is Equipped With " + artifactType);
                        break;
                    } else if (choice == 2) {
                        break;
                    }
                } else {
                    //                TODO refactor
                    Log.log(Colors.ANSI_RED + ":::ERROR::: Incorrect Choice, Try Again!" + Colors.ANSI_RESET);
                    Log.inputSign();
                }
            }
        }
    }


//    TODO move to model
    public static void goal() {
        if (Globals.hero.getXCoordinate() == Globals.grid.getSize() - 1 ||
                Globals.hero.getYCoordinate() == Globals.grid.getSize() - 1 ||
                Globals.hero.getXCoordinate() == 0 ||
                Globals.hero.getYCoordinate() == 0) {
//
//            TODO factor
            Log.log(Colors.ANSI_GREEN + "::: Congratutations, You Reached Your Goal! Do you want to continue? (Y)es (N)o" + Colors.ANSI_RESET);
            Scanner scanner = ConsoleController.getScanner();
            while(scanner.hasNextLine()) {
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("y") || input.equals("yes")) {
                    Globals.grid = GridFactory.generateMap(Globals.hero);
                    break;
                } else if (input.equals("n") || input.equals("no")) {
                    goodbye();
                    System.exit(-1);
                } else {
                    //               TODO refactor
                    Log.log(Colors.ANSI_RED + ":::ERROR::: Expected input (Y)es or (N)o, Try Again!!!" + Colors.ANSI_RESET);
                    Log.inputSign();
                }
            }
            Globals.GOAL_REACHED = true;
        } else {
            Globals.GOAL_REACHED = false;
        }
    }
}
