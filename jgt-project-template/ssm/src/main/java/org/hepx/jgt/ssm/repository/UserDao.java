package org.hepx.jgt.ssm.repository;

import org.apache.ibatis.annotations.Param;
import org.hepx.jgt.ssm.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * User: hepx
 * Date: 15-1-2
 * Time: 下午4:58
 */
@MyBatisRepository
public interface UserDao {

    User get(Long id);

    List<User> getAll();

    List<User> search(Map<String, Object> parameters);

    void save(User user);

    void update(User user);

    void delete(Long id);

    User findByLoginName(@Param("loginName")String loginName);
}
