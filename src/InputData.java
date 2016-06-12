
public enum InputData {
	// file, number of vertex, number of Edges, chromatic index
	G1("input/G1", 3, 2, 2),
	q8("input/q8", 64, 728, 9),

	G2("input/G2", 4, 3, 3),
	G3("input/G3", 4, 6, 3),
	G4("input/G4", 6, 6, 5),
	G5("input/G5", 6, 11, 5);

    private int chromaticIndex;
    private String fileName;
    private int numberOfEdges;
    private int numberOfVertexes;

    InputData(String fileName, int numberOfEdges, int numberOfVertexes, int chromaticIndex) {
        this.fileName = fileName;
        this.numberOfEdges = numberOfEdges;
        this.numberOfVertexes = numberOfVertexes;
        this.chromaticIndex = chromaticIndex;
    }

    public int getIndexChromatyczny() {
        return chromaticIndex;
    }
    public String getFileName() {
        return fileName;
    }

    public int getiloscKrawedzi() {
        return numberOfEdges;
    }

    public int getIloscWierzcholkow() {
        return numberOfVertexes;
    }


}
