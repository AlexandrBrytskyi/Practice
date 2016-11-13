package brytskyi.week6.sql;


import brytskyi.week6.sql.notebook_shop.model.exceptions.NotRegisteredTokenException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.NullTokenException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.UserAccessException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.controller_exceptions.WrongLoginDataException;
import brytskyi.week6.sql.notebook_shop.model.exceptions.dao_exceptions.NullFieldException;
import brytskyi.week6.sql.notebook_shop.model.production.*;
import brytskyi.week6.sql.notebook_shop.model.selling.Prodaja;
import brytskyi.week6.sql.notebook_shop.model.users.Buyer;
import brytskyi.week6.sql.notebook_shop.model.users.Contacts;
import brytskyi.week6.sql.notebook_shop.services.ICommonServiceWithToken;
import brytskyi.week6.sql.notebook_shop.services.additional.TokenGenerator;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class NotebookDBCrasher {

    static Logger LOGGER = Logger.getLogger(NotebookDBCrasher.class);
    ICommonServiceWithToken serviceWithToken;
    ExecutorService service;
    String token;
    volatile BlockingQueue<Integer[]> queue = new LinkedBlockingQueue<>();


    public NotebookDBCrasher(ICommonServiceWithToken serviceWithToken) throws WrongLoginDataException {
        this.serviceWithToken = serviceWithToken;
        service = Executors.newFixedThreadPool(50);
        token = serviceWithToken.login("admin", "admin");
    }

    public void startThreads() {
        for (int i = 0; i < 40; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            long start = System.currentTimeMillis();
                            serviceWithToken.addBuyer(new Buyer(new Contacts(TokenGenerator.getToken(),
                                    TokenGenerator.getToken(), TokenGenerator.getToken().substring(0,14))), token);
                            int diplId = serviceWithToken.addDisplay(new Display((int) start, (int) start), token).getId();
                            int hardId = serviceWithToken.addHardMemory(new HardMemory(TokenGenerator.getToken(), (int) start), token).getId();
                            int operId = serviceWithToken.addOperativeMemory(new OperativeMemory(TokenGenerator.getToken(), (int) start), token).getId();
                            int modelId = serviceWithToken.addNotebookModel(new NotebookModel(TokenGenerator.getToken(), TokenGenerator.getToken()), token).getId();
                            int procId = serviceWithToken.addProcessor(new Processor(TokenGenerator.getToken(), (int) start), token).getId();
                            int videoId = serviceWithToken.addVideoMemory(new VideoMemory(TokenGenerator.getToken(), (int) start), token).getId();
                            NotebookType nt = serviceWithToken.createNoteBookType(TokenGenerator.getToken(), (double) start, modelId, procId, hardId, operId, videoId, diplId, token);
                            serviceWithToken.priniatPartiyu(new LinkedList<NotebookForSail>() {{
                                for (int i = 0; i < 50; i++) {
                                    add(new NotebookForSail(nt, TokenGenerator.getToken(), null, NotebookState.NEW));
                                }
                            }}, nt.getId(), token);
                            queue.offer(new Integer[]{hardId, operId, procId, videoId, modelId, diplId});
                            LOGGER.info(Thread.currentThread().getId() + " Thread has added, time is " + (System.currentTimeMillis() - start));
                        } catch (NullFieldException e) {
                            LOGGER.error(e);
                        } catch (NullTokenException e) {
                            LOGGER.error(e);
                        } catch (NotRegisteredTokenException e) {
                            LOGGER.error(e);
                        } catch (UserAccessException e) {
                            LOGGER.error(e);
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }
                    }
                }
            });
        }


        for (int i = 0; i < 10; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Integer[] arr = queue.take();
                            long start = System.currentTimeMillis();
                            List<NotebookForSail> notes = serviceWithToken.getNotebooksByKriteria(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], 0, 0, token);
                            LOGGER.info(Thread.currentThread().getId() + " Thread has got from db, size " + notes.size() + ", time " + (System.currentTimeMillis() - start));
                        } catch (InterruptedException e) {
                            LOGGER.error(e);
                        } catch (UserAccessException e) {
                            LOGGER.error(e);
                        } catch (NotRegisteredTokenException e) {
                            LOGGER.error(e);
                        } catch (NullTokenException e) {
                            LOGGER.error(e);
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }
                    }
                }
            });
        }

        service.shutdown();
        while (!service.isTerminated()) {
            try {
                service.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
