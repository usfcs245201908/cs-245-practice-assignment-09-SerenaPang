import java.util.Arrays;
import java.util.NoSuchElementException;

class BinaryHeap
{

    private static final int n = 2;
    private int sizeOfHeap;
    private int[] binaryHeap;

  public BinaryHeap()
    {
        sizeOfHeap = 0;
        binaryHeap = new int[11];
        Arrays.fill(binaryHeap, -1);
    }

  
    public boolean isEmpty( )
    {
        return sizeOfHeap == 0;
    }

    public boolean isFull( )
    {
        return sizeOfHeap == binaryHeap.length;
    }


    protected int[] resize() {
        return Arrays.copyOf(binaryHeap, binaryHeap.length * 2);
    }



  
    private int parent(int i)
    {
        return (i - 1)/n;
    }

  
    private int Child(int i, int k)
    {
        return n * i + k;
    }

   
    public void add(int x)
    {
        if (isFull( ) ){
            binaryHeap = this.resize();
        }

       
        binaryHeap[sizeOfHeap++] = x;
        MovingNodeUp(sizeOfHeap - 1);
    }



    public int remove()
    {
        int keyItem = binaryHeap[0];
        remove(0);
        return keyItem;
    }


    public int remove(int ind)
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        int keyItem = binaryHeap[ind];
        binaryHeap[ind] = binaryHeap[sizeOfHeap - 1];
        sizeOfHeap--;
        MovingNodeDown(ind);
        return keyItem;
    }

    private void MovingNodeUp(int childInd)
    {
        int tmp = binaryHeap[childInd];
        while (childInd > 0 && tmp < binaryHeap[parent(childInd)])
        {
            binaryHeap[childInd] = binaryHeap[ parent(childInd) ];
            childInd = parent(childInd);
        }
        binaryHeap[childInd] = tmp;
    }


    private void MovingNodeDown(int ind)
    {
        int child;
        int tmp = binaryHeap[ ind ];
        while (Child(ind, 1) < sizeOfHeap)
        {
            child = minChild(ind);
            if (binaryHeap[child] < tmp)
                binaryHeap[ind] = binaryHeap[child];
            else
                break;
            ind = child;
        }
        binaryHeap[ind] = tmp;
    }

  
    private int minChild(int ind)
    {
        int bestChild = Child(ind, 1);
        int k = 2;
        int pos = Child(ind, k);
        while ((k <= n) && (pos < sizeOfHeap))
        {
            if (binaryHeap[pos] < binaryHeap[bestChild])
                bestChild = pos;
            pos = Child(ind, k++);
        }
        return bestChild;
    }

}