package org.hepx.jgt.common.tree;

import java.io.Serializable;
import java.util.List;

/**
 * 树节点
 *
 * @author hepx
 * @date 2016/1/12
 */
public class TreeNode implements Serializable {

    /**
     * 本节点ID
     */
    private Long id;
    /**
     * 节点名
     */
    private String name;
    /**
     * 父节点ID
     */
    private Long parentId;
    /**
     * 所有子节点
     */
    private List<TreeNode> children;

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
