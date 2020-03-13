import { TestBed } from '@angular/core/testing';

import { CouncilorService } from './councilor.service';

describe('CouncilorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CouncilorService = TestBed.get(CouncilorService);
    expect(service).toBeTruthy();
  });
});
