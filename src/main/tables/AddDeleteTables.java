package main.tables;

import java.sql.SQLException;

public class AddDeleteTables {

    public static void delete() throws SQLException {
        JournalInfoTable.DeleteTable();
        ArticleInfoTable.DeleteTable();
        VerdictTable.DeleteTable();
        CriticismsTable.DeleteTable();
        ErrorTable.DeleteTable();
        ResponseTable.DeleteTable();
        ArticleTable.DeleteTable();
        EditionTable.DeleteTable();
        VolumeTable.DeleteTable();
        JournalTable.DeleteTable();
        EditorInfoTable.DeleteTable();
        EditorTable.DeleteTable();
        QuestionTable.DeleteTable();
        ReviewTable.DeleteTable();
        ReviewerTable.DeleteTable();
        SubmissionInfoTable.DeleteTable();
        SubmissionTable.DeleteTable();
        AuthorTable.DeleteTable();
    }

    public static void adding() throws SQLException {
//        //Creates all tables
        UserTable.CreateUserTable();
        EditorTable.CreateEditorTable();
        ReviewerTable.CreateReviewerTable();
        JournalTable.CreateJournalTable();
        JournalInfoTable.CreateJournalInfoTable();
        VolumeTable.CreateVolumeTable();
        EditionTable.CreateEditionTable();
        AuthorTable.CreateAuthorTable();
        ArticleTable.CreateArticleTable();
        ArticleInfoTable.CreateArticleInfoTable();
        ReviewTable.CreateReviewTable();
        VerdictTable.CreateVerdictTable();
        ErrorTable.CreateErrorTable();
        QuestionTable.CreateQuestionTable();
        ResponseTable.CreateResponseTable();
        EditorInfoTable.CreateEditorInfoTable();
    }
    public static void main (String args[]) throws SQLException {

        //AddDeleteTables.adding();
        //AddDeleteTables.delete();
    }
}
