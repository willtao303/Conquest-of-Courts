public class Commands{
    public static final String NAME = "name";
    /*
     *  client -> server:
     *      - name
     * 
     *  server -> client:
     *      - 1: accepted, 0: rejected
     * 
     */
    public static final String JOIN_ROOM = "join";
    /*
     *  client -> server:
     *      - room code
     * 
     *  server -> client: N/A
     */

    public static final String CHAT_MSG = "chat";
    /*
     *  client -> server:
     *      - message
     * 
     *  server -> client:
     *      - latest message
     * 
     */

    public static final String USER_MOVE = "moveslot";
    /*
     *  client -> server:
     *      - which slot they are moving to
     *  
     *  server -> client:
     *      - which slot they are moving to
     *      - if spectator, also which index;
     */

    public static final String USER_RQMOVE = "rqmoveslot"; // request move
    /*
     *  client -> server: accept request
     *      - username other
     *  
     *  server -> client: send request
     *      - username other
     */


    public static final String USER_READY = "ready";
    /*
     *  client -> server:
     *      - no args
     *  
     *  server -> client: N/A
     */


}