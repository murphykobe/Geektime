public class PointSystem{

    public static void main(String[] args){
        PointView theView = new PointView();
        PointRepository theRepo = PointRepository.getInstance();
        PointController theController = new PointController(theRepo, theView);
        theView.setVisible(true);

    }
}