package main;

/**
 *
 * @author dieppv
 */
public class Element {
    /**
     * row index of element.
     */
    private int i = 0;

    /**
     * column index of element.
     */
    private int j = 0;

    /**
     * parent element.
     */
    private Element parentElement = null;

    /**
     * constructor.
     * @param row
     * @param col
     * @param element
     */
    public Element(final int row, final int col, final Element element) {
        this.i = row;
        this.j = col;
        this.parentElement = element;
    }

    /**
     * row index of element.
     * @return row index
     */
    public int getI() {
        return i;
    }

    /**
     * height index of element.
     * @return height index
     */
    public int getJ() {
        return j;
    }

    /**
     * get parent element of this element.
     * @return next element
     */
    public Element getParentElement() {
        return parentElement;
    }

    /**
     * set parent element.
     * @param element
     */
    public void setParentElement(final Element element) {
        this.parentElement = element;
    }
}
