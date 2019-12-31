
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

public class Metrics {
    // Map的key是接口名称，value对应接口请求的响应时间或时间戳；
    private Map<String, List<Long>> responseTimes = new HashMap<>();
    private Map<String, List<Long>> timestamps = new HashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
  
    public void recordResponseTime(String apiName, Long responseTime) {
      responseTimes.putIfAbsent(apiName, new ArrayList<>());
      responseTimes.get(apiName).add(responseTime);
    }
  
    public void recordTimestamp(String apiName, Long timestamp) {
      timestamps.putIfAbsent(apiName, new ArrayList<>());
      timestamps.get(apiName).add(timestamp);
    }
  
    public void startRepeatedReport(long period, TimeUnit unit){
      executor.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
          Map<String, Map<String, Long>> stats = new HashMap<>();
          for (Map.Entry<String, List<Long>> entry : responseTimes.entrySet()) {
            String apiName = entry.getKey();
            List<Long> apiRespTimes = entry.getValue();
            stats.putIfAbsent(apiName, new HashMap<>());
            stats.get(apiName).put("max", max(apiRespTimes));
            stats.get(apiName).put("avg", avg(apiRespTimes));
            stats.get(apiName).put("min", min(apiRespTimes));
          }
    
          for (Map.Entry<String, List<Long>> entry : timestamps.entrySet()) {
            String apiName = entry.getKey();
            List<Long> apiTimestamps = entry.getValue();
            stats.putIfAbsent(apiName, new HashMap<>());
            stats.get(apiName).put("count", (long)apiTimestamps.size());
          }
          System.out.println("stats"+stats);
        }
      }, 0, period, unit);
    }
  
    private Long max(List<Long> dataset) {
        return Collections.max(dataset);
    }
    private Long avg(List<Long> dataset) {
        Long sum = 0L;
        if(dataset.size()!=0){
            for(Long m:dataset){
                sum+=m;
            }
            return sum.longValue()/dataset.size();
        } 
        return sum;
    }
    private Long min(List<Long> dataset) {
        return Collections.min(dataset);
    }
  }