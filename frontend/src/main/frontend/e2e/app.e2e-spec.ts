import { TinkerbellPage } from './app.po';

describe('tinkerbell App', function() {
  let page: TinkerbellPage;

  beforeEach(() => {
    page = new TinkerbellPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
