public class UnitTest {

    public static void main(String[] args) {
      UserController controller = new UserController();
      int CALL_TIMES = 100000;
      for (int i = 0; i < CALL_TIMES; i++) {
        controller.login();
        controller.register();
      }
    }
  }