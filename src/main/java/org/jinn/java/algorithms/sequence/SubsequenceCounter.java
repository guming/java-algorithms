package org.jinn.java.algorithms.sequence;

import org.apache.log4j.Logger;

import com.jinn.netty.discard.DiscardClientHandler;

public class SubsequenceCounter {
	 private static final Logger logger = Logger.getLogger(
			 SubsequenceCounter.class.getName());
    private static char[] seq = null;
    private static char[] subseq = null;
    private static int[][] tbl = null;

    private SubsequenceCounter() {
    }

    public static int getCount(char[] sequence, char[] subSequence) {
        seq = sequence;
        subseq = subSequence;
        tbl = new int[seq.length + 1][subseq.length + 1];

        for (int row = 0; row < tbl.length; row++)
            for (int col = 0; col < tbl[row].length; col++)
                tbl[row][col] = countMatches(row, col);

        return tbl[seq.length][subseq.length];
    }

    private static int countMatches(int seqDigitsLeft, int subseqDigitsLeft) {
        if (subseqDigitsLeft == 0)
            return 1;

        if (seqDigitsLeft == 0)
            return 0;

        char currSeqDigit = seq[seq.length - seqDigitsLeft];
        char currSubseqDigit = subseq[subseq.length - subseqDigitsLeft];

        int result = 0;
        if (currSeqDigit == currSubseqDigit)
            result += tbl[seqDigitsLeft - 1][subseqDigitsLeft - 1];

        result += tbl[seqDigitsLeft - 1][subseqDigitsLeft];

        return result;
    }
    public static void main(String[] args) {
    	char[] seq1 = new char[] { 'G', 'A', 'C', 'V', 'X', 'T' };
    	char[]  seq2 = new char[] { 'A', 'G' };
    	SubsequenceCounter sc=new SubsequenceCounter();
    	logger.info("scounter :"+sc.getCount(seq1, seq2));
	}
}
