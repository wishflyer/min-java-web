package cn.dd.core.rest.result;

import java.io.Serializable;

public abstract class AbstractRESTResult<E extends Serializable> implements Serializable {

    private boolean handled = false;

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public abstract String toHttpResponse();

}
