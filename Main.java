import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int cantidadNumeros = 3000;
        int rangoMinimo = 0;
        int rangoMaximo = 10000;
        int[] numerosAleatorios = generarNumerosAleatorios(cantidadNumeros, rangoMinimo, rangoMaximo);
        mostrarNumerosAleatorios(numerosAleatorios);

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Gnome Sort");
            System.out.println("2. Merge Sort");
            System.out.println("3. Quick Sort");
            System.out.println("4. Radix Sort");
            System.out.println("5. Selection Sort");
            System.out.println("6. Shell Sort");
            System.out.println("7. Heap Sort");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gnomeSort(numerosAleatorios);
                    break;
                case 2:
                    mergeSort(numerosAleatorios);
                    break;
                case 3:
                    quickSort(numerosAleatorios, 0, numerosAleatorios.length - 1);
                    break;
                case 4:
                    radixSort(numerosAleatorios);
                    break;
                case 5:
                    selectionSort(numerosAleatorios);
                    break;
                case 6:
                    shellSort(numerosAleatorios);
                    break;
                case 7:
                    heapSort(numerosAleatorios);
                    break;
                case 8:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 8);

        scanner.close();
    }

    private static int[] generarNumerosAleatorios(int cantidad, int minimo, int maximo) {
        int[] numeros = new int[cantidad];
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            numeros[i] = random.nextInt(maximo - minimo + 1) + minimo;
        }

        return numeros;
    }

    private static void mostrarNumerosAleatorios(int[] numeros) {
        System.out.println("Números generados:");
        for (int i = 0; i < numeros.length; i++) {
            System.out.print(numeros[i] + " ");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }

    private static void gnomeSort(int[] array) {
        int index = 0;
        while (index < array.length) {
            if (index == 0) {
                index++;
            }
            if (array[index] >= array[index - 1]) {
                index++;
            } else {
                int temp = array[index];
                array[index] = array[index - 1];
                array[index - 1] = temp;
                index--;
            }
        }
        System.out.println("Gnome Sort completado.");
    }

    private static void mergeSort(int[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            mergeSort(left);
            mergeSort(right);

            merge(array, left, right);
        }
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
        System.out.println("Merge Sort completado.");
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    private static void radixSort(int[] array) {
        int max = getMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
        System.out.println("Radix Sort completado.");
    }

    private static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private static void countingSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }

    private static void selectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        System.out.println("Selection Sort completado.");
    }

    private static void shellSort(int[] array) {
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;

                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }
        System.out.println("Shell Sort completado.");
    }

    private static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
        System.out.println("Heap Sort completado.");
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;

            heapify(array, n, largest);
        }
    }
}
