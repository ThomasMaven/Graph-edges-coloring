public class GraphEdge {
    private int vertexOut;
    private int vertexIn;
    private int edgeNumber;

    public GraphEdge(int vertexOut, int vertexIn, int edgeNumber) {
        this.vertexOut = vertexOut;
        this.vertexIn = vertexIn;
        this.edgeNumber = edgeNumber;
    }

    public int getWierzcholekOut() {
        return vertexOut;
    }

    public int getWierzcholekIn() {
        return vertexIn;
    }
    
    public int getNumerKrawedzi(){
    	return edgeNumber;
    }
}
