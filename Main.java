public class Main {
    public static void main(String[] args) {
        MainGame game = new MainGame();
        Renderer render = new Renderer();
        render.setGame(game);
        render.setup();
        game.setup(render.input, render);
        game.run();
    }
}