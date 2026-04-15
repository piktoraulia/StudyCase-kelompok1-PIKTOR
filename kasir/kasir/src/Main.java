import service.TransaksiService;
import view.TransaksiView;

public class Main {
    public static void main(String[] args) {
        TransaksiService service = new TransaksiService();
        TransaksiView    view    = new TransaksiView(service);
        view.jalankan();
    }
}
