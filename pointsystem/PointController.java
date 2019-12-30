import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PointController{
    private PointRepository theRepo;
    private PointView theView;

    public PointController(PointRepository pointRepository, PointView pointView){
        this.theRepo = pointRepository;
        this.theView = pointView;
        this.theView.addTransactionListener(new TransactionListener());
        this.theView.addQueryListener(new QueryListener());
    }

    class QueryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0){

            try{
                Long id = theView.getId();
                Long r = allAvailablePoint(id);
                theView.setResult(r);
            }
            catch(NumberFormatException ex){
                theView.displayErrorMessage("Error Input");
            }

        }
    }

    class TransactionListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent arg0){

            Long id,credit,channel_id,event_id,expired_time = 0L; 
            try{
                id = theView.getId();
                credit = theView.getCredit();
                channel_id = theView.getChannelId();
                event_id = theView.getEventId();
                expired_time = theView.getExpiredTime();
                Long r = updatePoints(id, channel_id, event_id, credit, expired_time);
                theView.setResult(r);
            }
            catch(NumberFormatException ex){
                theView.displayErrorMessage("Error Input");
            }
        }

    }

    public Long updatePoints(Long id, Long channel_id, Long event_id, Long credit,Long expired_time){
        PointEntity point = theRepo.createPoint(id,channel_id,event_id,credit,expired_time);
        return theRepo.addPointTransaction(point);
    }

    public Long allAvailablePoint(Long id){
        List<PointEntity> entry = theRepo.getPointTransaction(id);
        Long sum = 0L;
        if(entry.size()==0) return sum;
        for(PointEntity p:entry){
            if(p.expired_time > p.create_time){
                sum+=p.credit;
            }
        }
        return sum;
    }

    public List<PointEntity> earnPointsDetail(Long id, int page){
        List<PointEntity> entry = theRepo.getPointTransaction(id);
        List<PointEntity> result = new ArrayList<PointEntity>();
        for(PointEntity p:entry){
            if(p.credit > 0L){
                result.add(p);
            }
        }
        return result;
    }

    public List<PointEntity> cosumePointsDetail(Long id, int page){
        List<PointEntity> entry = theRepo.getPointTransaction(id);
        List<PointEntity> result = new ArrayList<PointEntity>();
        for(PointEntity p:entry){
            if(p.credit < 0L){
                result.add(p);
            }
        }
        return result;
    }

}