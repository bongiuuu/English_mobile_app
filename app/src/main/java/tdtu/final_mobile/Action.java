package tdtu.final_mobile;

public class Action {
    String actionName;
    String actionPic;
    int layoutColor;

    public Action(String actionName, String actionPic, int layoutColor) {
        this.actionName = actionName;
        this.layoutColor = layoutColor;
        this.actionPic = actionPic;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getLayoutColor() {
        return layoutColor;
    }

    public void setLayoutColor(int layoutColor) {
        this.layoutColor = layoutColor;
    }

    public String getActionPic() {
        return actionPic;
    }

    public void setActionPic(String actionPic) {
        this.actionPic = actionPic;
    }
}
