package xyz.farmdashboard.server.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import xyz.farmdashboard.server.entity.UniswapTxEntity;
import xyz.farmdashboard.server.repositories.UniswapTxRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class UniswapDBService {
    private static final int SEPT_04 = 1599177600;
    private final UniswapTxRepository uniswapTxRepository;
    private final static Pageable LIMIT_100 = PageRequest.of(0, 100);

    public UniswapDBService(UniswapTxRepository uniswapTxRepository) {
        this.uniswapTxRepository = uniswapTxRepository;
    }

    public List<UniswapTxEntity> fetchAllForLastDay() {
        return uniswapTxRepository.fetchAllFromBlockDate(
            Instant.now().minus(1, DAYS).toEpochMilli() / 1000);
    }

//    public List<UniswapTxEntity> fetchAllForLastDay() {
//        return uniswapTxRepository.fetchAllLimited(LIMIT_100);
//    }

    public Iterable<UniswapTxRepository.OhlcProjection> ohclUniswapTx(String name) {
        return uniswapTxRepository.fetchOHLCTransactionsFromBlock(
            name, 1, 3600);
    }
}
