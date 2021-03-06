package xyz.farmdashboard.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.farmdashboard.server.dto.IncomeDTO;
import xyz.farmdashboard.server.dto.TvlHistoryDTO;
import xyz.farmdashboard.server.entity.HardWorkEntity;
import xyz.farmdashboard.server.entity.HarvestTvlEntity;
import xyz.farmdashboard.server.entity.HarvestTxEntity;
import xyz.farmdashboard.server.entity.RewardEntity;
import xyz.farmdashboard.server.entity.UniswapTxEntity;
import xyz.farmdashboard.server.repositories.HarvestTvlRepository;
import xyz.farmdashboard.server.repositories.UniswapTxRepository;
import xyz.farmdashboard.server.service.HardWorkDBService;
import xyz.farmdashboard.server.service.HarvestDBService;
import xyz.farmdashboard.server.service.IncomeDBService;
import xyz.farmdashboard.server.service.RewardDBService;
import xyz.farmdashboard.server.service.UniswapDBService;

import java.util.List;

@RestController
public class ApiController {
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);
    private final UniswapDBService uniswapDBService;
    private final HarvestDBService harvestDBService;
    private final HarvestTvlRepository harvestTvlRepository;
    private final IncomeDBService incomeDBService;
    private final HardWorkDBService hardWorkDBService;
    private final RewardDBService rewardDBService;

    public ApiController(UniswapDBService uniswapDBService,
                         HarvestDBService harvestDBService,
                         HarvestTvlRepository harvestTvlRepository,
                         IncomeDBService incomeDBService,
                         HardWorkDBService hardWorkDBService,
                         RewardDBService rewardDBService) {
        this.uniswapDBService = uniswapDBService;
        this.harvestDBService = harvestDBService;
        this.harvestTvlRepository = harvestTvlRepository;
        this.incomeDBService = incomeDBService;
        this.hardWorkDBService = hardWorkDBService;
        this.rewardDBService = rewardDBService;
    }

    @RequestMapping(value = "api/transactions/history/uni", method = RequestMethod.GET)
    public Iterable<UniswapTxEntity> uniswapHistoryData() {
        return uniswapDBService.fetchAllForLastDay();
    }

    @RequestMapping(value = "api/transactions/history/harvest", method = RequestMethod.GET)
    public Iterable<HarvestTxEntity> harvestHistoryData() {
        return harvestDBService.fetchAllForLastDay();
    }

    @RequestMapping(value = "api/transactions/history/alltvl", method = RequestMethod.GET)
    public Iterable<HarvestTvlEntity> allTvlHistoryData() {
        log.info("Request allTvlHistoryData");
        return harvestTvlRepository.getHistoryOfAllTvl();
    }

    @RequestMapping(value = "api/transactions/history/uni/ohcl/{name}", method = RequestMethod.GET)
    public Iterable<UniswapTxRepository.OhlcProjection> ohclUniswapTx(@PathVariable("name") String name) {
        return uniswapDBService.ohclUniswapTx(name);
    }

    @RequestMapping(value = "api/transactions/history/tvl/{name}", method = RequestMethod.GET)
    public Iterable<TvlHistoryDTO> tvlHistoryByVault(@PathVariable("name") String name) {
        log.info("Request tvlHistoryByVault " + name);
        return harvestDBService.fetchTvlByVault(name);
    }

    @Deprecated
    @RequestMapping(value = "api/transactions/history/income", method = RequestMethod.GET)
    public Iterable<IncomeDTO> incomeHistory() {
        log.warn("Request incomeHistory");
        return incomeDBService.fetchIncome();
    }

    @RequestMapping(value = "api/transactions/history/hardwork", method = RequestMethod.GET)
    public List<HardWorkEntity> historyHardWork() {
        return hardWorkDBService.getHistoryHardWorks();
    }

    @RequestMapping(value = "api/transactions/history/hardwork/{name}", method = RequestMethod.GET)
    public List<HardWorkEntity> historyHardWork(@PathVariable("name") String name) {
        return hardWorkDBService.getHistoryHardWorks(name);
    }

    @Deprecated
    @RequestMapping(value = "api/transactions/last/income", method = RequestMethod.GET)
    public IncomeDTO lastIncome() {
        log.warn("Request lastIncome");
        return incomeDBService.fetchLastIncome();
    }

    @RequestMapping(value = "api/transactions/last/harvest", method = RequestMethod.GET)
    public List<HarvestTxEntity> lastTvl() {
        return harvestDBService.fetchLastTvl();
    }

    @RequestMapping(value = "api/transactions/last/hardwork", method = RequestMethod.GET)
    public List<HardWorkEntity> lastHardWork() {
        return hardWorkDBService.getLastHardWorks();
    }

    @RequestMapping(value = "api/transactions/last/reward", method = RequestMethod.GET)
    public List<RewardEntity> lastReward() {
        return rewardDBService.getAllLastRewards();
    }
}
