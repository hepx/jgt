package org.hepx.tasksys.service;

import org.hepx.tasksys.entity.Reply;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-4-11
 * Time: 上午9:41
 */
public interface ReplyService {

    public int createReply(Reply reply);

    public int updateReply(Reply reply);

    public int deleteReply(Long replayId);

    public Reply findOne(Long replayId);

    public List<Reply> findAll();

    public List<Reply> findByTaskId(Long taskId);
}
