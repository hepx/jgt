package org.hepx.tasksys.service;

import org.hepx.tasksys.entity.Reply;
import org.hepx.tasksys.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: hepanxi
 * Date: 15-4-11
 * Time: 上午9:42
 */
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public int createReply(Reply reply) {
        return replyMapper.createReply(reply);
    }

    @Override
    public int updateReply(Reply reply) {
        return replyMapper.updateReply(reply);
    }

    @Override
    public int deleteReply(Long replayId) {
        return replyMapper.deleteReply(replayId);
    }

    @Override
    public Reply findOne(Long replayId) {
        return replyMapper.findOne(replayId);
    }

    @Override
    public List<Reply> findAll() {
        return replyMapper.findAll();
    }

    @Override
    public List<Reply> findByTaskId(Long taskId) {
        return replyMapper.findByTaskId(taskId);
    }
}
