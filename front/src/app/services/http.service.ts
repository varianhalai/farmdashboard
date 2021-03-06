import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SnackService} from './snack.service';
import {Observable} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {UniswapDto} from '../models/uniswap-dto';
import {HarvestDto} from '../models/harvest-dto';
import {HarvestTvl} from '../models/harvest-tvl';
import {HardWorkDto} from '../models/hardwork-dto';
import {RewardDto} from '../models/reward-dto';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private url = 'api/transactions';

  constructor(private http: HttpClient, private snackService: SnackService) {
  }

  getUniswapTxHistoryData(): Observable<UniswapDto[]> {
    return this.http.get<UniswapDto[]>(`${this.url}/history/uni`).pipe(
        catchError(this.snackService.handleError<UniswapDto[]>(`Uni history`))
    );
  }

  getUniswapTxHistoryByRange(minBlock:number,maxBlock:number): Observable<UniswapDto[]> {
    return this.http.get<UniswapDto[]>(`${this.url}/history/uni?from=${minBlock}&to=${maxBlock}`).pipe(
        catchError(this.snackService.handleError<UniswapDto[]>(`Uni history`))
    );
  }

  getHarvestTxHistoryData(): Observable<HarvestDto[]> {
    return this.http.get<HarvestDto[]>(`${this.url}/history/harvest`).pipe(
        catchError(this.snackService.handleError<HarvestDto[]>(`Harvest history`))
    );
  }

  getHardWorkHistoryData(): Observable<HardWorkDto[]> {
    return this.http.get<HardWorkDto[]>(`${this.url}/history/hardwork`).pipe(
        catchError(this.snackService.handleError<HardWorkDto[]>(`HardWork history`))
    );
  }

  getHardWorkHistoryDataByName(name: string): Observable<HardWorkDto[]> {
    return this.http.get<HardWorkDto[]>(`${this.url}/history/hardwork/` + name).pipe(
        catchError(this.snackService.handleError<HardWorkDto[]>(`HardWork history for ` + name))
    );
  }

  getLastTvls(): Observable<HarvestDto[]> {
    return this.http.get<HarvestDto[]>('api/transactions/last/harvest').pipe(
        catchError(this.snackService.handleError<HarvestDto[]>(`last TVL `))
    );
  }

  getHistoryAllTvl(): Observable<HarvestTvl[]> {
    return this.http.get<HarvestTvl[]>('api/transactions/history/alltvl').pipe(
        catchError(this.snackService.handleError<HarvestTvl[]>(`history all TVL`))
    );
  }

  getHistoryTvlByVault(vault: string): Observable<HarvestTvl[]> {
    return this.http.get<HarvestTvl[]>('api/transactions/history/tvl/' + vault).pipe(
        catchError(this.snackService.handleError<HarvestTvl[]>(`history TVL ` + vault))
    );
  }

  getLastHardWorks(): Observable<HardWorkDto[]> {
    return this.http.get<HardWorkDto[]>('api/transactions/last/hardwork').pipe(
        catchError(this.snackService.handleError<HardWorkDto[]>(`last HardWork `))
    );
  }

  getLastRewards(): Observable<RewardDto[]> {
    return this.http.get<RewardDto[]>('api/transactions/last/reward').pipe(
        catchError(this.snackService.handleError<RewardDto[]>(`last reward `))
    );
  }

  getAddressHistoryHarvest(address: string): Observable<HarvestDto[]> {
    return this.http.get<HarvestDto[]>(environment.apiEndpoint + '/history/harvest/' + address).pipe(
        catchError(this.snackService.handleError<HarvestDto[]>(`history address harvest `))
    );
  }

  getAddressHistoryUni(address: string): Observable<UniswapDto[]> {
    return this.http.get<UniswapDto[]>(environment.apiEndpoint + '/history/uni/' + address).pipe(
        catchError(this.snackService.handleError<UniswapDto[]>(`history address uni `))
    );
  }
}
