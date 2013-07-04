package com.engagepoint.university.ep2013b.browser.component;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: vladimir.ovcharov
* Date: 7/2/13
* Time: 3:17 PM
* To change this template use File | Settings | File Templates.
*/
@FacesComponent("browserComponentTree")
public class BrowserComponentTree extends UINamingContainer {
    private TreeNode root;
    private List<BrowserItem> browserItemsList;
    private boolean isSelected = false;

    public BrowserComponentTree() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String requestId = request.getParameter("id");

        browserItemsList = Service.getItems();
        root = new DefaultTreeNode("Root", null);
        createNode(browserItemsList, root, requestId);

        if (!isSelected && root.getChildCount() > 0) {
            root.getChildren().get(0).setSelected(true);
        }
    }

    private void createNode(List<BrowserItem> list, TreeNode node, String requestId) {
        for (BrowserItem item : list) {

            TreeNode tempRoot = new DefaultTreeNode(item, node);
            if (item.getId().equals(requestId)) {
                tempRoot.setSelected(true);
                isSelected = true;
                tempRoot.setExpanded(true);


                TreeNode parent = tempRoot.getParent();
                while (parent != null) {
                    parent.setExpanded(true);
                    parent = parent.getParent();
                }
            }
            if (item.getChildren().size() != 0) {
                createNode(item.getChildren(), tempRoot, requestId);
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public List<BrowserItem> getBrowserItemsList() {
        return browserItemsList;
    }

}