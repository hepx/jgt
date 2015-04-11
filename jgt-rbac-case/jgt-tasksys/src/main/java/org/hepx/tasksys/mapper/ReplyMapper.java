package org.hepx.tasksys.mapper;

import org.hepx.tasksys.entity.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-3-28
 * Time: 上午9:39
 */
@Repository
public interface ReplyMapper {
    
    public int createReply(Reply reply);

    public int updateReply(Reply reply);

    public int deleteReply(Long replayId);

    public Reply findOne(Long replayId);

    public List<Reply> findAll();

    public List<Reply> findByTaskId(Long taskId);

}
