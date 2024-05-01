package be.ugent.objprog.ugentopoly.logs;

import be.ugent.objprog.ugentopoly.logging.LogElement;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class LogListView extends ListView {
    public LogListView(ObservableList<LogElement> logs) {
        super(logs);
        setSelectionModel(null);

        logs.addListener(this::onChanged);
    }

    // Always scroll down to the most recently added element, so the user does not have to scroll manually.
    private void onChanged(ListChangeListener.Change<? extends LogElement> c) {
    while (c.next()) {
        if (c.wasAdded()) {
            scrollTo(c.getList().size() - 1);
        }
    }
}
}