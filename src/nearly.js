var global

function setup() {
  const url = 'https://api.immomo.com/v2/nearby/people/lists'
  const targetClass = Java.use('com.immomo.momo.protocol.http.a.a')
  const http = targetClass.$new()
  const args = generateParams()
  const response = http.doPost(url, args)
  global = deepParse(JSON.parse(response))
}

function generateParams() {
  const HashMap = Java.use("java.util.HashMap")
  const root = HashMap.$new()

  root.put('acc', 0)
  root.put('gapps', 0)
  root.put('mmuid', '')
  root.put('realauth', 0)
  root.put('screen', '1440x2560')
  root.put('device_type', 'android')
  root.put('osversion_int', 32)
  root.put('ddian_active_time', 1734228084)
  root.put('online_time', 1)
  root.put('business_version', 2)
  root.put('constellation', 0)
  root.put('model', 'V2241A')
  root.put('net', 1)
  root.put('androidId', '81365be91a60f9d4')
  root.put('lat', 28.196451)
  root.put('age_max', 100)
  root.put('_uid_', 'a3931e93ff9cb0bc16e38cf3a14aa599')
  root.put('phone_type', 'GSM')
  root.put('refreshmode', 'user')
  root.put('lng', 112.977302)
  root.put('count', 20)
  root.put('age_min', 18)
  root.put('index', 0)
  root.put('_iid', '66d0f1f6ff93133db312ce775d3bd460')
  root.put('version', 12475)
  root.put('apksign', '4f3a531caff3e37c278659cc78bfaecc')
  root.put('_net_', 'wifi')
  root.put('router_mac', 'a2:2b:a1:13:ed:2d')
  root.put('network_class', 'wifi')
  root.put('vip_filter', 0)
  root.put('loctime', 1734227889373000)
  root.put('buildnumber', 'V417IR/370')
  root.put('_ab_test_', 'nearbypeopleliveexp-kmjyjy_blanklocation-spxwuo_blanktest-rsxyxo_blankmorenew-wkhqld_Aactive-wklfmo_blank')
  root.put('save', 'YES')
  root.put('locater', 201)
  root.put('imsi', 'unknown')
  root.put('moment_sex', '')
  root.put('emu', '029f181d6e7ba188885c78462623c37a')
  root.put('loctype', 1)
  root.put('mac', '02:00:00:00:00:00')
  root.put('manufacturer', 'vivo')
  root.put('rom', 12)
  root.put('uid', 'a3931e93ff9cb0bc16e38cf3a14aa599')
  root.put('total', 0)
  root.put('native_ua', 'Mozilla/5.0 (Linux Android 12 V2241A Build/V417IR wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/95.0.4638.74 Safari/537.36')
  root.put('market_source', 1)
  root.put('oaid', '')
  root.put('social', 0)
  root.put('phone_netWork', 0)
  root.put('dpp', '7e1d2da06380bd1d1cc05624433c5600')
  root.put('sex', 'F')
  root.put('lua_version', 3.0)
  root.put('firstRefresh', 0)
  root.put('_uidType', 'androidid')

  return root
}

function deepParse(obj) {
  if (Array.isArray(obj)) {
      return obj.map(deepParse)
  } else if (typeof obj === 'object' && obj !== null) {
      Object.keys(obj).forEach(function (key) {
          var value = obj[key]
          if (typeof value === 'string') {
              try {
                  obj[key] = deepParse(JSON.parse(value))
              } catch (e) { }
          } else {
              obj[key] = deepParse(value)
          }
      })
  }
  return obj
}

rpc.exports = {
  nearly: function() {
    Java.perform(function() {
      setup()
    })
    return global
  }
}
