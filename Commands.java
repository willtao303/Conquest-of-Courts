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
    public static final String USER_SIDE = "moveside";
    /*
     *  client -> server:
     *      - which side they are moving to
     *  
     *  server -> client:
     *      - which side they are moving to
     */

    public static final String USER_MOVE = "moveslot";
    /*
     *  client -> server:
     *      - which slot they are moving to
     *  
     *  server -> client:
     *      - which slot they are moving to
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

    public static final String START = "start";
    /*
     *  client -> server: N/A
     * 
     *  server -> client: 
     *      - no args
     */
}