package de.blogsiteloremipsum.gamingbets.communication.server;

import de.blogsiteloremipsum.gamingbets.communication.CommunicationPackage;
import static de.blogsiteloremipsum.gamingbets.communication.communication_types.*;


/**
 * Created by Felix on 17.11.2015.
 */
public class CommunicationPackageHandler {

    private CommunicationPackage cp;

    public CommunicationPackageHandler(CommunicationPackage cp) {
        this.cp = cp;
    }

    public String handle() {
        //TODO Needs Implementation
        /**
         * Returns "done" when Succesfully done Transaction
         * Otherwise returns Error as String
         */
        switch (cp.getType()){
            case EDIT:
                break;
            case EDITADMIN:
                break;
            case LOGIN:
                break;
            case LOGOUT:
                break;
            case REGISTER:
                break;
            case PLACEBET:
                break;
            default:
                break;


        }

        cp.getType();

        return "done";
    }

    public CommunicationPackage getCp() {
        return cp;
    }

    public void setCp(CommunicationPackage cp) {
        this.cp = cp;
    }
}
