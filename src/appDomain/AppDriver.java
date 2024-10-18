package appDomain;

import shapes.*;
import utils.SortingUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AppDriver {
    public static void main(String[] args) {
        String fileName = null;
        char compareType = 'h'; // Default is height
        char sortMethod = 'b'; // Default is bubble sort

        // Parse command line arguments
        for (String arg : args) {
            if (arg.toLowerCase().startsWith("-f")) {
                fileName = arg.substring(2);
            } else if (arg.toLowerCase().startsWith("-t")) {
                compareType = arg.charAt(2); // Get the character after '-t' or '-T'
            } else if (arg.toLowerCase().startsWith("-s")) {
                sortMethod = arg.charAt(2); // Get the character after '-s' or '-S'
            }
        }

        // Validate the required parameters
        if (fileName == null || (compareType != 'v' && compareType != 'h' && compareType != 'a') ||
            (sortMethod != 'b' && sortMethod != 's' && sortMethod != 'i' && sortMethod != 'm' && sortMethod != 'q' && sortMethod != 'z')) {
            System.out.println("Usage: java -jar Sort.jar -f<filename> -t<v/h/a> -s<b/s/i/m/q/z>");
            return;
        }

        Shape[] shapes = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            if ((line = br.readLine()) != null) {
                int numberOfShapes = Integer.parseInt(line.trim());
                shapes = new Shape[numberOfShapes];
            }

            int index = 0;
            while ((line = br.readLine()) != null && index < shapes.length) {
                String[] parts = line.trim().split("\\s+");
                String shapeType = parts[0];
                double height = Double.parseDouble(parts[1]);
                double secondParam = Double.parseDouble(parts[2]); // Either radius or side

                switch (shapeType) {
                    case "Cylinder":
                        shapes[index++] = new Cylinder(height, secondParam);
                        break;
                    case "Cone":
                        shapes[index++] = new Cone(height, secondParam);
                        break;
                    case "Pyramid":
                        shapes[index++] = new Pyramid(height, secondParam);
                        break;
                    case "SquarePrism":
                        shapes[index++] = new SquarePrism(height, secondParam);
                        break;
                    case "TriangularPrism":
                        shapes[index++] = new TriangularPrism(height, secondParam);
                        break;
                    case "PentagonalPrism":
                        shapes[index++] = new PentagonalPrism(height, secondParam);
                        break;
                    case "OctagonalPrism":
                        shapes[index++] = new OctagonalPrism(height, secondParam);
                        break;
                    default:
                        System.out.println("Unknown shape type: " + shapeType);
                        break;
                }
            }

            // Sorting shapes based on compareType
            switch (compareType) {
                case 'h':
                    if (sortMethod == 'b') {
                        SortingUtils.bubbleSort(shapes); // Bubble sort for height
                    } else if (sortMethod == 's') {
                        SortingUtils.selectionSort(shapes); // Selection sort for height
                    } else if (sortMethod == 'i') {
                        SortingUtils.insertionSort(shapes); // Insertion sort for height
                    }
                    break;
                case 'v':
                    if (sortMethod == 'b') {
                        SortingUtils.bubbleSort(shapes); // Bubble sort for volume
                    } else if (sortMethod == 's') {
                        SortingUtils.selectionSort(shapes); // Selection sort for volume
                    } else if (sortMethod == 'i') {
                        SortingUtils.insertionSort(shapes); // Insertion sort for volume
                    }
                    break;
                case 'a':
                    if (sortMethod == 'b') {
                        SortingUtils.bubbleSort(shapes); // Bubble sort for base area
                    } else if (sortMethod == 's') {
                        SortingUtils.selectionSort(shapes); // Selection sort for base area
                    } else if (sortMethod == 'i') {
                        SortingUtils.insertionSort(shapes); // Insertion sort for base area
                    }
                    break;
                default:
                    System.out.println("Invalid comparison type. Use 'v', 'h', or 'a'.");
                    return;
            }

            // Display the first, last, and every thousandth element
            displaySortedElements(shapes);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }
    }

    private static void displaySortedElements(Shape[] shapes) {
        System.out.println("\nDisplaying sorted elements:");

        int length = shapes.length;

        if (length > 0) {
            // First element
            System.out.println("First element is: " + shapes[0].getClass().getSimpleName() + " has a Volume of: " + shapes[0].calcVolume());

            // Every thousandth element
            for (int i = 1000; i < length; i += 1000) {
                System.out.println(i + "-th element is: " + shapes[i - 1].getClass().getSimpleName() + " has a Volume of: " + shapes[i - 1].calcVolume());
            }

            // Last and second last element
            if (length > 1) {
                System.out.println("Second last element is: " + shapes[length - 2].getClass().getSimpleName() + " has a Volume of: " + shapes[length - 2].calcVolume());
            }
            System.out.println("Last element is: " + shapes[length - 1].getClass().getSimpleName() + " has a Volume of: " + shapes[length - 1].calcVolume());
        }
    }
}
