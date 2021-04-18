import io.javalin.Javalin;
import controllers.*;
import io.javalin.core.util.Header;

class Main {

    static int PORT = 80;

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(PORT);

        // Account Management

        app.post("/login", AccountController::login);
        app.post("/register", AccountController::register);

        // Information Getters

        app.get("/get-pcos", InformationController::getAllPcos);




        // Set some properties such as headers, etc.

        app.before(ctx -> {

            // Set JSON header for all requests

            ctx.contentType("application/json");

            // Set CORS to * so that everyone can access the API

            ctx.header(Header.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        });

    }

}