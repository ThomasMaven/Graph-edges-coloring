
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


import com.softtechdesign.ga.*;

public class GraphColoring extends GAStringsSeq {
	

    static String[] possibleColors;
    static int[] graphVertexes;
    static ArrayList<GraphEdge> graphEdgesArrayList;
    static List<String> listOfPossibleColors;

    public GraphColoring(int numberOfEdges) throws GAException {
        super(
        		numberOfEdges, //number of genes in chromosome
                500, //population of N chromosomes
                0.8, ///crossover probability
                10, //random selection chance % (regardless of fitness)
                1000, //stop after N generations
                0, //num prelim runs
                0, //max prelim generations
                0.2, //chromosome mutation prob.
                0, //number of decimal places in chrom (0 means treat chrom as integer)
                possibleColors, //gene space (possible gene values)
                Crossover.ctTwoPoint, //crossover type
                true); //compute statisitics
    }
    
    @Override
    protected double getFitness(int index) {
        ChromStrings chromosom = getChromosome(index);
        String genes[] = chromosom.getGenes();
        Set<String> usedColors = new HashSet<>(Arrays.asList(genes));
        for (GraphEdge graphEdge : graphEdgesArrayList) {
        	
        	for (GraphEdge graphEdge2 : graphEdgesArrayList) {
        		
        		//check if edges are "neighbours" to verify if we need to chcech if coloring is valid.
        		if(graphEdge.getWierzcholekIn()==graphEdge2.getWierzcholekIn() && graphEdge.getWierzcholekOut()!=graphEdge2.getWierzcholekOut()){
        			if(genes[graphEdge.getNumerKrawedzi()-1]==genes[graphEdge2.getNumerKrawedzi()-1]) {
        				//if coloring is not valid return 0 - lowest possible vaule of Fittness function
        				return 0;
        			}
        		} else if(graphEdge.getWierzcholekOut()==graphEdge2.getWierzcholekOut() && graphEdge.getWierzcholekIn()!=graphEdge2.getWierzcholekIn()){
        			if(genes[graphEdge.getNumerKrawedzi()-1]==genes[graphEdge2.getNumerKrawedzi()-1]) {
        				//if coloring is not valid return 0 - lowest possible vaule of Fittness function
        				return 0;
        			}
        		} else if(graphEdge.getWierzcholekIn()==graphEdge2.getWierzcholekOut() && graphEdge.getWierzcholekOut()!=graphEdge2.getWierzcholekIn()){
        			if(genes[graphEdge.getNumerKrawedzi()-1]==genes[graphEdge2.getNumerKrawedzi()-1]) {
        				//if coloring is not valid return 0 - lowest possible vaule of Fittness function
        				return 0;
        			}
        		}

        	}
        }
        return (1.0/usedColors.size());
    }
    
    public static void main(String[] args) throws GAException, FileNotFoundException, InterruptedException {
    	
    	//load file
        InputData currentData = InputData.G3;
        String fileName = currentData.getFileName();
        Scanner myScanner = new Scanner(new FileReader(fileName));
        Set<Integer> edges = new HashSet<>();
        int numberOfEdges = myScanner.nextInt();
        graphEdgesArrayList = new ArrayList<>(numberOfEdges);
        
        //scan until there is new line avalible
        while (myScanner.hasNextLine()) {

            int edgeNumber = myScanner.nextInt();
            int vertexFrom = myScanner.nextInt();
            int vertexTo = myScanner.nextInt();
        	myScanner.nextLine();
            edges.add(vertexFrom);
            edges.add(vertexTo);
            graphEdgesArrayList.add(new GraphEdge(vertexFrom, vertexTo, edgeNumber));
        }
        myScanner.close();
        int numberOfVertexes = edges.size();
        graphVertexes = new int[numberOfVertexes];
        for (Integer i : edges) {
            graphVertexes[i-1] = i;
        }
        possibleColors = new String[numberOfEdges];
        for (int i = 0; i < numberOfEdges; i++) {
            possibleColors[i] = "k"+i;
        }

        GraphColoring GAObjekt = new GraphColoring(graphEdgesArrayList.size());
        Thread th = new Thread(GAObjekt);
        th.start();
        //wait until processing is finished
        th.join();
        if (!th.isAlive()) {
            if (GAObjekt.getFittestChromosomesFitness() != 0) {
                
            	Set<String> wykorzystaneKolory = new HashSet<>(graphVertexes.length);
                ChromStrings chromosom = (ChromStrings) GAObjekt.getFittestChromosome();
                String genes[] = chromosom.getGenes();

                Collections.addAll(wykorzystaneKolory, genes);
                int iloscKolorowUzytych = wykorzystaneKolory.size();
            	System.out.println("Udało się prawidłowo pokolorować graf z wykorzystaniem " + iloscKolorowUzytych + ". \nIdneks chromatyczny dla wczytanego grafu wynosi " + currentData.getIndexChromatyczny());
            	
            }
            else {
            	System.out.println("Nie udało się prawidłowo pokolorować grafu");
            }

        }

    }



}