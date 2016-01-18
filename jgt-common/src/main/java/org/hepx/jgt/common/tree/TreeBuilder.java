package org.hepx.jgt.common.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构构建类
 * 注：树必须要符合TreeNode的数据结构:id,parentId,children
 * 输出：将一个无序List<TreeNode>能过此类的build方法返回一个List<TreeNode>的树形数据结构
 * (相应的子节点children将被填充)。
 *
 * @author hepx
 * @date 2016/1/12
 */
public class TreeBuilder {

    /**
     * 将一个无序List<TreeNode>能过此类的build方法返回一个List<TreeNode>的树形数据结构
     * (相应的子节点children将被填充)。
     * @param nodeList
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> nodeList) {
        List<TreeNode> newNodeList = new ArrayList<TreeNode>();
        for (TreeNode node1 : nodeList) {
            boolean mark = false;
            for (TreeNode node2 : nodeList) {
                if (node1.getParentId() != null && node1.getParentId().equals(node2.getId())) {
                    mark = true;
                    if (node2.getChildren() == null) {
                        node2.setChildren(new ArrayList<TreeNode>());
                    }
                    node2.getChildren().add(node1);
                    break;
                }
            }
            if (!mark) {
                newNodeList.add(node1);
            }
        }
        return newNodeList;
    }

}
