import io.javalin.Javalin;
import controllers.*;

class Main {

    static int PORT = 80;

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(PORT);

        app.get("/login", AccountController.login);
        app.get("/register", AccountController.register);

    }

}