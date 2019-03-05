package acg.architecture.view.glyph.loader;

import java.awt.Color;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GlyphLoader {
    private String path;

    public GlyphLoader(String filename) {
        this.path = filename;
    }

    public LayoutBundle load() throws IOException, InvalidLayoutException {
        List<List<EntryEdge>> edgeLists = new ArrayList<>();
        List<EntryEdge> curList = new ArrayList<>();
        List<EntryCircle> circleList = new ArrayList<>();
        EntryMap<EntryVertex> vertices = new EntryMap<>();
        EntryMap<EntryColor> colors = new EntryMap<>();

        File layoutFile = fileHelper(path);
        Scanner fin = new Scanner(layoutFile);
        int lineNumber = 0;
        String line;
        String[] args;


        while (fin.hasNextLine()) {
            line = fin.nextLine();
            args = line.split(",");

            //If line contains only commas, we do not parse it but store our current edge list
            //Could this also a apply to circle lists? How does this handle the final creation of the vertex and color lists?
            if (args.length == 0) {
                if (curList.size() > 0) {
                    edgeLists.add(curList);
                    curList = new ArrayList<>();
                }
            } else {
                //We ignore commented lines
                if (!args[0].contains(";")) {
                    switch (args[0].toLowerCase()) {
                        case "c":
                            colors.addEntry(createColor(args, lineNumber));
                            break;
                        case "v":
                            vertices.addEntry(createVertex(args, lineNumber));
                            break;
                        case "o":
                            //TODO the 'index'
                            //there might be more than one circle list
                            //So when starting a list, initialize the index.
                            //circleList.add(createCircle(index, args, lineNumber, vertices, colors))
                            break;
                        case "e":
                            //If the current list of EntryEdges is empty:
                              //Initialize the index
                            //Get the vertex from the next entry, and pass both to create an edge.
                            //EntryEdge toAdd = createEdge(index, args, vertexStart,lineNumber, vertices, colors)
                            //TODO : create EntryEdge add to curList
                            //curList.add();
                            break;
                        default:
                            //Unknown type present in file
                            throw new InvalidLayoutException(lineNumber);
                    }
                }
            }
            lineNumber++;
        }

        //reached EOF, we store any remaining edges
        if (curList.size() > 0) {
            edgeLists.add(curList);
        }

        fin.close();
        return new LayoutBundle(edgeLists, circleList);
    }


    private static File fileHelper(String path) throws IOException {
        File toReturn = new File(path);
        if (!toReturn.exists() || !toReturn.canRead()) {
            throw new IOException("Specified GlyphLoader filename cannot be found/read.");
        }
        return toReturn;
    }

    private static EntryColor createColor(String[] args, int lineNumber) throws InvalidLayoutException {
        checkArguments(args, lineNumber, 3);

        String colorHex = "0X" + args[2].substring(1);
        Color color = Color.decode(colorHex);
        int index = Integer.parseInt(args[1]);

        return new EntryColor(index, color);
    }

    private static EntryVertex createVertex(String[] args, int lineNumber) throws InvalidLayoutException {
        checkArguments(args, lineNumber, 4);

        int index = Integer.parseInt(args[1]);
        double x = Double.parseDouble(args[2]);
        double y = Double.parseDouble(args[3]);
        double z = Double.parseDouble(args[4]);

        return new EntryVertex(index, x, y, z);
    }

    private static EntryCircle createCircle(int index, String[] args, int lineNumber, EntryMap<EntryVertex> vertices, EntryMap<EntryColor> colors) throws InvalidLayoutException {

        checkArguments(args, lineNumber, 4);
        int color = Integer.parseInt(args[3]);
        int vertex = Integer.parseInt(args[1]);
        double radius = Double.parseDouble(args[2]);

        if (!colors.containsEntry(color)) {
            throw new InvalidLayoutException("Color does not exist:", lineNumber);
        }
        if (!vertices.containsEntry(vertex)) {
            throw new InvalidLayoutException("Vertex does not exist: ", lineNumber);
        }
        return new EntryCircle(index, vertices.getEntry(vertex), radius, colors.getEntry(color));
    }

    private static void checkArguments(String[] args, int lineNumber, int params) throws InvalidLayoutException {
        if (args.length < params) {
            // Invalid number of arguments for the creation of Entry object
            throw new InvalidLayoutException("Syntax Error on Line:", lineNumber);
        }
        else
        {
            for (int i = 0; i < params; i++) {
                if (args[i].contains(";")) {
                    throw new InvalidLayoutException("Syntax Error: Unexpected Comment on Line:", lineNumber);
                }
            }
        }
    }

    private static EntryEdge createEdge(int index, String[] args, int vertexStart, int lineNumber, EntryMap<EntryVertex> vertices, EntryMap<EntryColor> colors) throws InvalidLayoutException {
        //vertexStart is fed as an argument. It is the connecting vertex. 
        checkArguments(args, lineNumber, 3);
        int color = Integer.parseInt(args[2]);
        int vertexEnd = Integer.parseInt(args[1]);

        if (!colors.containsEntry(color)) {
            throw new InvalidLayoutException("Color does not exist.", lineNumber);
        }
        if (!vertices.containsEntry(vertexEnd) || !vertices.containsEntry(vertexStart)) {
            throw new InvalidLayoutException("Vertex does not exist. ", lineNumber);
        }
        return new EntryEdge(index, vertices.getEntry(vertexStart), vertices.getEntry(vertexEnd), colors.getEntry(color));
    }

    @Override
    public String toString() {
        return "GlyphLoader: path=" + path;
    }
}//Class

