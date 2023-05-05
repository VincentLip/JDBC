package org.example;

import jdk.jshell.spi.ExecutionControl;
import org.example.DAO.EvenementDAO;
import org.example.DAO.LieuDAO;
import org.example.model.Evenement;
import org.example.model.Lieu;
import org.example.util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class IHM {
    Scanner scanner;
    String choix;

    private Connection connection;

    private LieuDAO lieuDAO;

    private EvenementDAO evenementDAO;

    public IHM(){scanner = new Scanner(System.in);}

    public void start() throws SQLException {
        do {
            menu();
            choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    createLieuAction();
                    break;
                case "2":
                    editLieuAction();
                    break;
                case "3":
                    deleteLieuAction();
                    break;
                case "4":
                    createEvenementAction();
                    break;
                case "5":

                    break;
                case "6":
                    deleteEvenementAction();
                    break;

            }
        }while (!choix.equals("0"));
    }

    private void menu() {
        System.out.println("1 - Ajouter un lieu ");
        System.out.println("2 - Modifier un lieu ");
        System.out.println("3 - Supprimer un lieu ");
        System.out.println("4 - Ajouter un événement ");
        System.out.println("5 - Modifier un événement ");
        System.out.println("6 - Supprimer un événement ");
        System.out.println("7 - Ajouter un client ");
        System.out.println("8 - Modifier un client ");
        System.out.println("9 - Supprimer un client ");
        System.out.println("0 - Quitter ");
    }

    private Lieu createLieuAction() {
        Lieu lieu = null;
        System.out.println("Donner les informations concernant le lieu");
        System.out.print("Merci de saisir le nom : ");
        String name = scanner.nextLine();
        System.out.print("Merci de saisir l'adresse : ");
        String adress = scanner.nextLine();
        System.out.print("Merci de saisir la capacité: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        lieu = new Lieu(name, adress, capacity);
        try {
            connection = new DataBaseManager().getConnection();
            connection.setAutoCommit(false);
            lieuDAO = new LieuDAO(connection);
            if(lieuDAO.save(lieu)) {
                System.out.println("Lieu ajouté "+ lieu.getId());
                connection.commit();
            }else {
                lieu = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            lieu = null;
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return lieu;
    }


    private Lieu getLieuAction() {
        System.out.print("Merci de saisir l'id du lieu: ");
        int id =  scanner.nextInt();
        scanner.nextLine();
        try {
            connection = new DataBaseManager().getConnection();
            lieuDAO = new LieuDAO(connection);
            Lieu lieu = lieuDAO.getById(id);
            System.out.println(lieuDAO);
            if(lieu == null) {
                System.out.println("Aucun lieu avec cet id");
            }
            return lieu;
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void deleteLieuAction() {
        Lieu lieu = getLieuAction();
        try {
            connection = new DataBaseManager().getConnection();
            lieuDAO = new LieuDAO(connection);
            if(lieu != null && lieuDAO.delete(lieu)) {
                System.out.println("Lieu supprimé");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    private void editLieuAction() {
        Lieu lieu = getLieuAction();
        if(lieu != null) {
            System.out.print("Merci de saisir le nom : ");
            lieu.setName(scanner.nextLine());
            System.out.print("Merci de saisir l'adresse : ");
            lieu.setAdress(scanner.nextLine());
            System.out.print("Merci de saisir la capacité: ");
            lieu.setCapacity(scanner.nextInt());
            scanner.nextLine();

            try {
                connection = new DataBaseManager().getConnection();
                lieuDAO = new LieuDAO(connection);
                connection.setAutoCommit(false);
                if(lieuDAO.update(lieu)) {
                    System.out.println("Mise à jour effectuée");
                    connection.commit();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ExecutionControl.NotImplementedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Evenement createEvenementAction() throws SQLException {
        Evenement evenement = null;
        System.out.println("Donner les informations concernant l'événement");
        scanner.nextLine();
        System.out.print("Merci de saisir le nom de l'evenement : ");
        String name = scanner.nextLine();
        System.out.print("Merci de saisir la date: ");
        String date = scanner.nextLine();
        System.out.print("Merci de saisir l'heure: ");
        String hour = scanner.nextLine();
        System.out.print("Merci de saisir le prix: ");
        Float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Merci de saisir le nombre de ticket: ");
        int nbTicket = scanner.nextInt();
        scanner.nextLine();
        Lieu lieu = getLieuAction();
        evenement = new Evenement(name, hour, date,lieu,price,nbTicket);
        try {
            connection = new DataBaseManager().getConnection();
            connection.setAutoCommit(false);
            evenementDAO = new EvenementDAO(connection);
            if(evenementDAO.save(evenement)) {
                System.out.println("Lieu ajouté "+ evenement.getId());
                connection.commit();
            }else {
                evenement = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            evenement = null;
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        return evenement;
    }
    private Evenement getEvenementAction() {
        System.out.print("Merci de saisir l'id de l'evenement: ");
        int id =  scanner.nextInt();
        scanner.nextLine();
        try {
            connection = new DataBaseManager().getConnection();
            evenementDAO = new EvenementDAO(connection);
            Evenement evenement = evenementDAO.getById(id);
            System.out.println(evenementDAO);
            if(evenement == null) {
                System.out.println("Aucun lieu avec cet id");
            }
            return evenement;
        } catch (SQLException | ExecutionControl.NotImplementedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void deleteEvenementAction() {
        Evenement evenement = getEvenementAction();
        try {
            connection = new DataBaseManager().getConnection();
            evenementDAO = new EvenementDAO(connection);
            if(evenement != null && evenementDAO.delete(evenement)) {
                System.out.println("Evenement supprimé");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }

}
