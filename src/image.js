var url

function setup(uuid) {
  const MoliveKit = Java.use('com.immomo.molive.foundation.util.ay')
  url = MoliveKit.e(uuid)
}

rpc.exports = {
  image: (uuid) => {
    Java.perform(() => {
      setup(uuid)
    })
    return url
  }
}
