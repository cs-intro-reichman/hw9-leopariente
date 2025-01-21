public class Test {

    public static final TestHandler testHandler = new TestHandler();

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        MemoryBlock block = new MemoryBlock(10, 1000);
        list.add(0, block);
        MemoryBlock block2 = new MemoryBlock(20, 2000);
        list.add(0, block2);
        MemoryBlock block3 = new MemoryBlock(30, 3000);
        list.add(1, block3);
        MemoryBlock block4 = new MemoryBlock(40, 4000);
        list.add(3, block4);
        String expected = "true";
        String actual = "";
        try {
            actual += list.getFirst().block.equals(block2) && list.getLast().block.equals(block4) && list.getSize() == 4
                    && compareLinkedLists(list, createLinkedList(new MemoryBlock[] { block2, block3, block, block4 }));
        } catch (Exception e) {
            actual = TesterMessagesEnum.ERROR + e.getMessage();
        }
        if (args.length < 1) {
            System.out.println("Usage: java Test <Question Name/number>");
        } else {
            TesterQuestionEnum question = TesterQuestionEnum.valueOf(args[0]);
            testHandler.questionDecider(question, args);
        }
        System.out.println(list.getLast().block.baseAddress);
        System.out.println(list.getFirst().block.baseAddress);
        System.out.println(list.getSize());
        System.out.println(list.toString());
        System.out.println(createLinkedList(new MemoryBlock[] { block2, block3, block, block4 }).toString());
        System.out.println(actual + " " + expected);
    }

    /// HELPER FUNCTIONS
    public static LinkedList createLinkedList(MemoryBlock[] blocks) {
        LinkedList list = new LinkedList();
        for (int i = blocks.length - 1; i >= 0; i--) {
            list.addFirst(blocks[i]);
        }
        return list;
    }

    public static boolean compareLinkedLists(LinkedList list1, LinkedList list2) {
        if (list1.getSize() != list2.getSize()) {
            return false;
        }
        ListIterator iterator1 = list1.iterator();
        ListIterator iterator2 = list2.iterator();
        while (iterator1.hasNext()) {
            MemoryBlock block1 = iterator1.next();
            MemoryBlock block2 = iterator2.next();
            if (!block1.equals(block2)) {
                return false;
            }
        }
        return true;
    }
}
