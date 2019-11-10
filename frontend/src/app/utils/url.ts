export function parseQuerySearch(search = '') {
  return search.substr(1).split('&').filter(t => t).reduce((out, paramPair) => {
    let [key, val] = paramPair.split('=');
    key = decodeURIComponent(key);
    val = decodeURIComponent(val);
    out[key] = val;
    return out;
  }, {});
}

export function getQuerySearch(params, {clean = false} = {}) {
  return '?' + Object.keys(params)
    .filter(key => !(clean && params[key] === undefined))
    .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(params[key]))
    .join('&');
}

export function appendQueryParam(search = '', key, value) {
  const params = parseQuerySearch(search);
  params[key] = value;
  return getQuerySearch(params);
}
