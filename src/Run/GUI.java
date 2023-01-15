package Run;

import InOUT.IO;
import Manager.Account;
import Manager.Customer;
import Manager.Login;
import Manager.Operation;
import java.util.Date;
import java.util.Random;

public class GUI {
    public static void main(String[] args) {
        do {
            try {
                menu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(NullPointerException ex){}
        } while (true);
    }
    /**la methode qui va nous generer un nombre aleatoire**/
    public static int Random_number(){
        Random random = new Random();
        int nb;
        nb = random.nextInt(1000);
        return nb;
    }
    /**la methode qui va nous generer un string aleatoire**/
    public static String Random_string(){
        String chars = "695101443298ABcdefGHIJKLMXWKDE";
        String pass = "";
        for(int x=0;x<15;x++)
        {
            int i = (int)Math.floor(Math.random() * 10);
            pass += chars.charAt(i);
        }
        return pass;
    }
    /**la methode qui va faire le versement sur un compte**/
    public static double versement(double montant_a_verser){
        if (montant_a_verser>0) {
            System.out.print("\n***** Versement effectué avec Succès *****\n");
            System.out.print("\nLe Montant versé est  "+montant_a_verser+" FBU");
            return (montant_a_verser + Account.getBalance_account());
        }else
            System.err.print("\nImpossible de Verser "+montant_a_verser+" FBU Sur ce compte");
        return 1;
    }
    /**la methode qui va faire le retrait sur un compte**/
    public static double retrait(double montant_a_retirer){
        if (montant_a_retirer>0){
            System.out.print("\n***** Retrait effectué avec Succès *****\n");
            System.out.print("\nLe Montant retiré est  "+montant_a_retirer+" FBU");
            return (Account.getBalance_account()-montant_a_retirer);
        }
        System.err.print("\nImpossible de Retirer "+montant_a_retirer+" FBU Sur ce compte\n");
        return 1;
    }
    static void menu() throws InterruptedException {

        System.out.print("\n\t*****     WELCOME   TO    CRYPTBANK    *****\n");
        System.out.print("\n            1.Se Connecter A Votre Compte Agent");
        System.out.print("\n            2.Se faire inscire en tant qu'agent");
        System.out.print("\n            3.Quitter et Fermer Le Logiciel");
        switch (IO.setINT("\n\n         Veuillez Choisir une action :")){
            case 1:
                if (Login.getListe_des_agents().size()>0){
                     String Username=IO.setString("\nVotre Nom d' utilisateur :");String password=IO.setString("\nVotre mot de passe :");
                    for (int i = 0; i < Login.getListe_des_agents().size(); i++) {
                    if (Username.contentEquals(Login.getListe_des_agents().get(i).getUsername()) &&
                            password.contentEquals(Login.getListe_des_agents().get(i).getPassword())){
                        System.out.print("\n-------->>>Verifiaction De vos Identifiants......Patientez");
                        Thread.sleep(5000);
                        System.out.print("\n-------->>>"+Username+"  Bienvenue Sur Notre PlateForme un moment ....");
                        Thread.sleep(5000);
                        System.out.print("\n-------->>>Chargement Des Informations Sur Votre Compte\n");
                        Thread.sleep(5000);
                        /**cette boucle while et la variable stop permettent à l'utilisateur de rester connecter durant les operations**/
                        boolean stop=false;
                        while(!stop){
                        System.out.print("\n        1.Enregistrer un nouveau client");
                        System.out.print("\n        2.Effectuer une Operation Bancaire");
                        System.out.print("\n        3.Afficher la liste des clients de la banque");
                        System.out.print("\n        4.Afficher l'Historique des Transactions");
                        System.out.print("\n        5.Acceder au Service Forex");
                        System.out.print("\n        6.Se Deconnecter De Votre Compte");
                        switch (IO.setINT("\n\n     Choisir une Action svp :")){
                            case 1:
                                System.out.print("\nDEFINIR LES INFORMATIONS PERSONNELLES DU CLIENT\n");
                                Customer new_customer= new Customer(IO.setINT("\nDefinir identifiant Client:"),IO.setString("\nSaisir Nom Client :"),IO.setString("\nSaisir Prenom Client :"));
                                System.out.print("\n\n-------->>>LA CREACTION DU COMPTE EST EN COURS...");
                                Thread.sleep(3000);
                                System.out.print("\n\n-------->>>SAUVEGARDE DES DONNEES EN COURS...");
                                Account new_account=new Account(new_customer.getId_customer(), Random_string(),0,new_customer);
                                Thread.sleep(3000);
                                System.out.print("\n\n~~~~~~~~LE COMPTE A ETE CREE AVEC SUCCES~~~~~~~~\n\n");
                                System.out.print("\nIdentifiant du client -->"+new_account.getId_account()+"\nNom du client -->"+new_customer.getName()+"\nPrenom du client -->"
                                        +new_customer.getPost_name()+"\nNumero de compte -->"+new_account.getNumber_account()+"\n");
                                break;
                            case 2:
                                System.out.print("\nPreciser Les Informations Du Client\n");
                                Customer current_customer=new Customer(IO.setINT("\nId Client :"),IO.setString("\nNom Client :"),IO.setString("\nPrenom Client :"));
                                Account current_account=new Account(Random_number(),Random_string(),Account.getBalance_account(), current_customer);
                                System.out.print("\n1.Versement Sur Un Compte \t 2.Retrait Sur Un Compte\n");
                                switch (IO.setINT("\nChoisir Une Action A executer :")){
                                    case 1:
                                        Operation versement_op=new Operation(new Date(),"Versement",versement(IO.setFLOAT(
                                                "Montant A verser :")),current_account);
                                        break;
                                    case 2:
                                        Operation retrait_op=new Operation(new Date(),"RETRAIT",+retrait(IO.setFLOAT("Montant A Retirer :")),current_account);
                                        break;
                                }
                                break;
                            case 3:
                                Account.display_all_account();
                                break;
                            case 4:
                                Operation.display_all_operations();
                                break;
                            case 5:
                                System.out.print("\n            Bienvenue sur Le Service Forex . . .");
                                Thread.sleep(3000);
                                System.out.print("\nTAUX DE CHANGE DU "+ new Date());
                                System.out.print("\nACHETER DES DEVISES");
                                System.out.print("\n#######################");
                                System.out.print("\nDOLLAR A 3000 FBU");
                                System.out.print("\nEURO A 3500 FBU");
                                System.out.print("\nYOUAN A 4000 FBU");
                                System.out.print("\n#######################");
                                System.out.print("\nVENDRE DES DEVISES");
                                System.out.print("\n#######################");
                                System.out.print("\nDOLLAR A 1800 FBU");
                                System.out.print("\nEURO A 2000 FBU");
                                System.out.print("\nYOUAN A 2850 FBU");
                                System.out.print("\n########################\n");
                                switch (IO.setINT("\n***  1.FBU -->DOLLAR  ***\n***  2.FBU --> EURO  ***\n***  3.FBU --> Youan  ***\n***  4.DOLLAR-->FBU  ***\n***  5.EURO -->FBU  ***\n***  6.FBU -->Youan  ***\n\nChoisr Une Action :")){
                                    case 1:
                                            long montant_dollar=IO.setINT("\nIndiquer Votre montant en FBU:");
                                            long m_recu_dollar=montant_dollar/3000;
                                            System.out.print("\nVotre montant en DOLLAR est de :"+m_recu_dollar+"\n");
                                        break;
                                    case 2:
                                        long montant_euro=IO.setINT("\nIndiquer Votre montant en FBU:");
                                        long m_recu_euro=montant_euro/3500;
                                        System.out.print("\nVotre montant en EURO est de :"+m_recu_euro);
                                        break;
                                    case 3:
                                        long montant_Youan=IO.setINT("\nIndiquer Votre montant en FBU:");
                                        long m_recu_youan=montant_Youan/4000;
                                        System.out.print("\nVotre montant en YOUAN est de :"+m_recu_youan+"\n");
                                        break;
                                    case 4:
                                        long montant1=IO.setINT("\nIndiquer Votre montant en DOLLAR:"+"\n");
                                        long m_recu1=montant1*1800;
                                        System.out.print("\nVotre montant en FBU est de :"+m_recu1+"\n");
                                        break;
                                    case 5:
                                        long montant2=IO.setINT("\nIndiquer Votre montant en EURO:"+"\n");
                                        long m_recu2=montant2*2000;
                                        System.out.print("\nVotre montant en FBU est de :"+m_recu2+"\n");
                                        break;
                                    case 6:
                                        long montant=IO.setINT("\nIndiquer Votre montant en YOUAN:");
                                        long m_recu=montant*2850;
                                        System.out.print("\nVotre montant en FBU est de :"+m_recu);
                                        break;
                                    default:
                                        System.out.print("\n*****ERREUR DE SAISIE*****");
                                }
                                break;
                            case 6:
                                System.out.print("\n.......Deconnecxion en Cours.......un moment\n");
                                Thread.sleep(3000);
                                menu();
                                break;
                            default:
                                System.err.print("\n***** Erreur De La Saisie *****\n");
                        }
                        }
                    }
                }
                }else
                    System.err.print("\nPas d'Agent inscrit\n");
                    break;
            case 2:
                System.out.print("\nVOUS DEVEZ DEFINIR VOS INFORMATIONS DE CONNECTION");
                Login new_login =new Login(IO.setString("\nVotre Nom d'utilisateur:"),IO.setString("\nDefinir votre mot de passe :"));
                String passverify=IO.setString("\nConfirmez votre Mot de Passe :");
                if (passverify.contentEquals(new_login.getPassword())){
                    System.out.print("\nEnregistrement de Votre Compte Dans Le Système . . ...\n");
                    Thread.sleep(5000);
                    System.out.print("\nVos Identifiant pour la connexion sont\n\n");
                    System.out.print("\nNom de L'agent --->"+new_login.getUsername()+"\nVotre Mot de Passe --->"+ new_login.getPassword()+"\n");
                }else
                    System.err.print("\n****Erreur Lors de La verification de votre Mot de Passe****\n");
                break;
            case 3:
                System.out.print("\n\nPLEASE WAIT THE SYSTEM WILL DISCONNECT YOU . . ....\n");
                Thread.sleep(5000);
                System.out.print("\n\n---------->>>>THANK YOU<<<<----------\n\n");
                System.exit(0);
                break;
            default:
                System.err.print("\n*****ERREUR DE SAISIE*****\n");
            }
    }
}