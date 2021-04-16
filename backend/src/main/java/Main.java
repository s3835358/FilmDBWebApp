import io.javalin.Javalin;
import controllers.*;

class Main {

    static int PORT = 80;

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(PORT);

        app.post("/login", AccountController::login);
        app.post("/register", AccountController::register);

    }

}