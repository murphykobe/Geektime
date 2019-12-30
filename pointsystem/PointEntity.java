import java.util.Date;

class PointEntity{
    public Long id;
    public Long channel_id;
    public Long event_id;
    public Long credit;
    public final Long create_time;
    public Long expired_time;

    public PointEntity(Long id, Long channel_id, Long event_id, Long credit, Long expired_time){
        this.id=id;
        this.channel_id=channel_id;
        this.credit=credit;
        this.create_time=new Date().getTime();
        this.expired_time=this.create_time+expired_time;
    }
}