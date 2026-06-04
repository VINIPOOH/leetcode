public class WordSearch79 {

    private char[][] board;
    // מגדירים מערך של כיוון לחיפוש לעומק
    private int[][] directions = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
    private String word;
    private int rovs;
    private int cols;

    private int wordLastIndex;


    public boolean exist(char[][] board, String word) {
        rovs = board.length;
        cols = board[0].length;
        this.board = board;
        this.word = word;
        this.wordLastIndex = word.length()-1;
        //בשביל כל תא עושים בדיקה אם יש מלה שמתחיל מי אותה.
        for (int rov = 0; rov < rovs; rov++) {
            for (int col = 0; col < cols; col++) {
                if (dfs(rov, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    //
    private boolean dfs(int rov, int col, int currentDepth) {
        //אם שורה או עמודה נמצות חוץ מתחום אז מפסיקים בדיקה
        if (rov >= rovs || col >= cols || rov < 0 || col < 0) {
            return false;
        }
        //אם אות לא זהה אז גם מפסיקים בדיקה
        if (board[rov][col] != word.charAt(currentDepth)) {
            return false;
        }
        //אם זהים אז מצאנו פתרון
        if (currentDepth == wordLastIndex) {
            return true;
        }
        //מציבים בפיבוט ערך מצתא כדי לשמור לבדיקות הבאות
        char pivot = board[rov][col];
        board[rov][col] = '!';
        for (int[] direction : directions) {
            if (dfs(rov + direction[0], col + direction[1], currentDepth + 1)) {
                return true;
            }
        }
        board[rov][col] = pivot;
        return false;
    }
}
