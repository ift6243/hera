package com.udem.ift6243.oracle;

public final class Oracle
{
    private static volatile Oracle instance = null;

    /**
     * Constructeur de l'objet.
     */
    private Oracle() {
        super();
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance de l'oracle.
     */
    public final static Oracle getInstance() {
        if (Oracle.instance == null) {
           synchronized(Oracle.class) {
             if (Oracle.instance == null) {
            	 Oracle.instance = new Oracle();
             }
           }
        }
        return Oracle.instance;
    }
}
