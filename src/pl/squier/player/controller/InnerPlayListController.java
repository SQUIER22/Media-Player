package pl.squier.player.controller;

import javafx.scene.control.TextArea;
import javafx.scene.input.TransferMode;

/**
 * Created by SQUIER
 * on 2015-10-16.
 */
public class InnerPlaylistController {

    public InnerPlaylistController(TextArea innerPlaylist) {

        setOnDragOver(innerPlaylist);
        setOnDragExit(innerPlaylist);
    }

    private void setOnDragExit(TextArea innerPlaylist) {
        innerPlaylist.setOnDragExited(e -> {
            innerPlaylist.setStyle("-fx-border-width: 0px");
        });
    }

    private void setOnDragOver(TextArea innerPlaylist) {
        innerPlaylist.setOnDragOver(e -> {
            boolean isAccepted = false;

		/*
		 * checking if files extensions are .mp3
		 */
            for (int i = 0; i < e.getDragboard().getFiles().size(); i++) {
                isAccepted = e.getDragboard().getFiles().get(i).toString().toLowerCase().endsWith(".mp3");
            }

        /*
		 * if all files ends with .mp3 dragDrop has green light,
		 * if not: play list will flash with red light
		 */
            if (isAccepted) {
                innerPlaylist.setStyle("-fx-border-color: #00ff00; -fx-border-width: 1.5px;"
                        + " -fx-border-radius: 0.5em; -fx-background-radius: 0.5em;");
                e.acceptTransferModes(TransferMode.LINK);

            } else {
                innerPlaylist.setStyle("-fx-border-color: #ff0000; -fx-border-width: 1.5px;"
                        + " -fx-border-radius: 0.5em; -fx-background-radius: 0.5em;");
                e.consume();

            }
        });
    }

}
