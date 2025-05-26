package br.com.dio;

import br.com.dio.model.Board;
import br.com.dio.model.BoardCard;
import br.com.dio.model.BoardColumn;
import br.com.dio.repository.BoardCardRepository;
import br.com.dio.repository.BoardColumnRepository;
import br.com.dio.repository.BoardRepository;
import br.com.dio.service.BoardCardService;
import br.com.dio.service.BoardColumnService;
import br.com.dio.service.BoardService;
import br.com.dio.persistance.config.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseManager.init();
            Connection connection = DatabaseManager.getConnection();

            BoardRepository boardRepository = new BoardRepository(connection);
            BoardColumnRepository columnRepository = new BoardColumnRepository(connection);
            BoardCardRepository cardRepository = new BoardCardRepository(connection);

            BoardService boardService = new BoardService(boardRepository);
            BoardColumnService columnService = new BoardColumnService(columnRepository);
            BoardCardService cardService = new BoardCardService(cardRepository);

            Scanner scanner = new Scanner(System.in);

            Board selectedBoard = null;
            while (selectedBoard == null) {
                List<Board> boards = boardService.listBoards();
                System.out.println("Boards:");
                for (int i = 0; i < boards.size(); i++) {
                    System.out.println((i + 1) + " - " + boards.get(i).getName());
                }
                System.out.println((boards.size() + 1) + " - Create new board");
                System.out.print("Select a board or create a new one: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (choice >= 1 && choice <= boards.size()) {
                    selectedBoard = boards.get(choice - 1);
                } else if (choice == boards.size() + 1) {
                    System.out.print("Enter new board name: ");
                    String boardName = scanner.nextLine();
                    Board newBoard = new Board();
                    newBoard.setName(boardName);
                    boardService.createBoard(newBoard);
                    System.out.println("Board created.");
                } else {
                    System.out.println("Invalid option.");
                }
            }

            boolean running = true;
            while (running) {
                System.out.println("\n--- Board Menu ---");
                System.out.println("1 - Move card to next column");
                System.out.println("2 - Cancel a card");
                System.out.println("3 - Create a card");
                System.out.println("4 - Block a card");
                System.out.println("5 - Unblock a card");
                System.out.println("6 - Close board");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (option) {
                    case 1:
                        System.out.print("Enter card ID to move: ");
                        int cardIdToMove = scanner.nextInt();
                        scanner.nextLine();
                        BoardCard cardToMove = cardService.getCardById(cardIdToMove);
                        BoardColumn currentColumn = columnService.getColumnById(cardToMove.getColumnId());
                        List<BoardColumn> columns = columnService.listColumnsByBoardId(selectedBoard.getId());
                        columns.sort((a, b) -> Integer.compare(a.getPosition(), b.getPosition()));
                        int currentIndex = -1;
                        for (int i = 0; i < columns.size(); i++) {
                            if (columns.get(i).getId() == currentColumn.getId()) {
                                currentIndex = i;
                                break;
                            }
                        }
                        if (currentIndex != -1 && currentIndex < columns.size() - 1) {
                            BoardColumn nextColumn = columns.get(currentIndex + 1);
                            cardToMove.setColumnId(nextColumn.getId());
                            cardService.updateCard(cardToMove);
                            System.out.println("Card moved to column: " + nextColumn.getName());
                        } else {
                            System.out.println("Card is already in the last column.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter card ID to cancel: ");
                        int cardIdToCancel = scanner.nextInt();
                        scanner.nextLine();
                        cardService.deleteCard(cardIdToCancel);
                        System.out.println("Card canceled (deleted).");
                        break;
                    case 3:
                        System.out.print("Enter column ID to add card: ");
                        int columnId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter card title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter card description: ");
                        String description = scanner.nextLine();
                        BoardCard newCard = new BoardCard();
                        newCard.setColumnId(columnId);
                        newCard.setTitle(title);
                        newCard.setDescription(description);
                        newCard.setCreatedAt(LocalDateTime.now());
                        newCard.setBlocked(false);
                        cardService.createCard(newCard);
                        System.out.println("Card created.");
                        break;
                    case 4:
                        System.out.print("Enter card ID to block: ");
                        int cardIdToBlock = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter block reason: ");
                        String blockReason = scanner.nextLine();
                        BoardCard cardToBlock = cardService.getCardById(cardIdToBlock);
                        cardToBlock.setBlocked(true);
                        cardToBlock.setBlockReason(blockReason);
                        cardService.updateCard(cardToBlock);
                        System.out.println("Card blocked.");
                        break;
                    case 5:
                        System.out.print("Enter card ID to unblock: ");
                        int cardIdToUnblock = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter unblock reason: ");
                        String unblockReason = scanner.nextLine();
                        BoardCard cardToUnblock = cardService.getCardById(cardIdToUnblock);
                        cardToUnblock.setBlocked(false);
                        cardToUnblock.setUnblockReason(unblockReason);
                        cardService.updateCard(cardToUnblock);
                        System.out.println("Card unblocked.");
                        break;
                    case 6:
                        running = false;
                        System.out.println("Board closed.");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
            DatabaseManager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}