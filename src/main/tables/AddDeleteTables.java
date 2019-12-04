package main.tables;

import java.sql.SQLException;

public class AddDeleteTables {
    public static void main (String args[]) throws SQLException {
        //Deletes all tables
//        JournalInfoTable.DeleteTable();
//        ArticleInfoTable.DeleteTable();
//        VerdictTable.DeleteTable();
//        CriticismsTable.DeleteTable();
//        ErrorTable.DeleteTable();
//        ResponseTable.DeleteTable();
//        ArticleTable.DeleteTable();
//        EditionTable.DeleteTable();
//        VolumeTable.DeleteTable();
//        JournalTable.DeleteTable();
//        EditorInfoTable.DeleteTable();
//        EditorTable.DeleteTable();
//        QuestionTable.DeleteTable();
//        ReviewTable.DeleteTable();
//        ReviewerTable.DeleteTable();
//        SubmissionInfoTable.DeleteTable();
//        SubmissionTable.DeleteTable();
//        AuthorTable.DeleteTable();

        //Creates all tables
        JournalTable.CreateJournalTable();
        VolumeTable.CreateVolumeTable();
        EditionTable.CreateEditionTable();
        EditorTable.CreateEditorTable();
        JournalInfoTable.CreateJournalInfoTable();
        SubmissionTable.CreateSubmissionTable();
        AuthorTable.CreateAuthorTable();
        SubmissionInfoTable.CreateSubmissionInfoTable();
        ArticleTable.CreateArticleTable();
        ArticleInfoTable.CreateArticleInfoTable();
        ReviewerTable.CreateReviewerTable();
        ReviewTable.CreateReviewTable();
        EditorInfoTable.CreateEditorInfoTable();
        VerdictTable.CreateVerdictTable();
        CriticismsTable.CreateCriticismsTable();
        ErrorTable.CreateErrorTable();
        QuestionTable.CreateQuestionTable();
        ResponseTable.CreateResponseTable();

    }
}
