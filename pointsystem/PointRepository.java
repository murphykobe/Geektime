import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class PointRepository {

    public Map<Long,List<PointEntity>> pointEntities;
    private static PointRepository single_instance = null; 

    public PointRepository(){
        pointEntities = new HashMap<Long,List<PointEntity>>();
    }
    
    public static PointRepository getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new PointRepository(); 
  
        return single_instance; 
    } 
    public PointEntity createPoint(Long id, Long channel_id, Long event_id, Long credit,Long expired_time){
        return new PointEntity(id, channel_id, event_id, credit,expired_time);
    }

    public Long addPointTransaction(PointEntity point){
        if(pointEntities.containsKey(point.id)){
            List<PointEntity> curUserPoint = pointEntities.get(point.id);
            curUserPoint.add(point);
            pointEntities.put(point.id,curUserPoint);
        }else{
            List<PointEntity> curUserPoint = new ArrayList<PointEntity>();
            curUserPoint.add(point);
            pointEntities.put(point.id,curUserPoint);
        }
        return point.id;
    }

    public List<PointEntity> getPointTransaction(Long id){
        return pointEntities.get(id);
    }

}